package com.smmc.vyrl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Jeonguk Lee
 */
@SpringBootApplication
@ComponentScan(value = {"com.smmc.vyrl"})
@EnableAutoConfiguration(exclude = {
        Neo4jDataAutoConfiguration.class,
        DataSourceAutoConfiguration.class
})
public class SpringBootMysqlNeo4jApplicaion {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMysqlNeo4jApplicaion.class, args);
    }

}
