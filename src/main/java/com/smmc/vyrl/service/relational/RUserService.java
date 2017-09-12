package com.smmc.vyrl.service.relational;

import com.smmc.vyrl.domain.relational.RUser;
import com.smmc.vyrl.repo.relational.RUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class RUserService {

    @Autowired
    private RUserRepository rUserRepository;

    public RUser getUser(BigInteger id) {
        return rUserRepository.findOne(id);
    }
}
