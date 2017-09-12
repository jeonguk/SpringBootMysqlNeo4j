package com.smmc.vyrl.repo.graph;

import com.smmc.vyrl.domain.graph.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends GraphRepository<User> {
    User findByNickname(@Param("nickname") String nickname);

    @Query(" MATCH (user:User {nickname: {0} }) - [f:FOLLOWING] -> (follow:User) RETURN  user")
    User findUserFollow(String nickname);

    @Query("MATCH (user:User) - [r:FOLLOWING] -> (follow:User) WHERE ID(user) = {0} AND ID(follow) = {1} RETURN user")
    User isFollow(long userId, long followingUserId);

    @Query("MATCH (a:User), (b:User) WHERE ID(a) = {0} AND ID(b) = {1} " +
            "CREATE (a) - [r:FOLLOWING] -> (b)" +
            "RETURN a ")
    User followingUser(long userId, long followingUserId);

}

