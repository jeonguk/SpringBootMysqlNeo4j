package com.smmc.vyrl.config;

import com.smmc.vyrl.SpringBootMysqlNeo4jApplicaion;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.smmc.vyrl.repo.graph")
@EnableJpaRepositories(basePackages = "com.smmc.vyrl.repo.relational", transactionManagerRef = "mysqlTransactionManager")
@EntityScan(basePackageClasses = {SpringBootMysqlNeo4jApplicaion.class, Jsr310JpaConverters.class})
@EnableTransactionManagement
public class DataSourceConfig {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceConfig.class);

    private static final String DEFAULT_NAMING_STRATEGY = "org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy";

    @Bean
    public org.neo4j.ogm.config.Configuration getConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config
                .driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI("http://neo4j:vyrl2@192.168.56.101:7474");
        return config;
    }

    @Bean
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory(getConfiguration(), "com.smmc.vyrl.domain.graph");
    }


    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    @Primary
    @Bean
    @Autowired
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource())
                .packages("com.smmc.vyrl.domain.relational")
                .build();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Autowired
    @Bean(name = "neo4jTransactionManager")
    public Neo4jTransactionManager neo4jTransactionManager() throws Exception {
        return new Neo4jTransactionManager(getSessionFactory());
    }

    @Autowired
    @Primary
    @Bean(name = "mysqlTransactionManager")
    public JpaTransactionManager mysqlTransactionManager(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactory(builder).getObject());
    }

    @Autowired
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(Neo4jTransactionManager neo4jTransactionManager, JpaTransactionManager mysqlTransactionManager) {
        return new ChainedTransactionManager(
                mysqlTransactionManager,
                neo4jTransactionManager
        );
    }

}
