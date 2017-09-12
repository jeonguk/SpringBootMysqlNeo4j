package com.smmc.vyrl.repo.relational;

import com.smmc.vyrl.domain.relational.RUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.math.BigInteger;

public interface RUserRepository extends JpaRepository<RUser, BigInteger>, QueryDslPredicateExecutor<RUser> {
}
