package com.smmc.vyrl.service.graph;

import com.smmc.vyrl.domain.graph.User;
import com.smmc.vyrl.repo.graph.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getUserList() {
        Iterable<User>  userList = userRepository.findAll();
        return userList;
    }

    public User getUserFollow(String nickname) {
        return userRepository.findUserFollow(nickname);
    }

    public User getUser(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public void saveFollowUser(long userId, long followingUserId) {
        log.info(""+userId);
        log.info(""+followingUserId);
        User user = userRepository.isFollow(userId, followingUserId);
        if(user == null) {
            userRepository.followingUser(userId, followingUserId);
        }
    }

    public User getUser(long userId) {
        User result = userRepository.findOne(userId);
        return result;
    }
}
