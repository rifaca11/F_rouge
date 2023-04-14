package com.filrouge.designflow.spring.repository;

import com.filrouge.designflow.spring.model.Post;
import com.filrouge.designflow.spring.model.User;
import com.filrouge.designflow.spring.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
