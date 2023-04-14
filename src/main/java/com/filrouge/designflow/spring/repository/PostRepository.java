package com.filrouge.designflow.spring.repository;

import com.filrouge.designflow.spring.model.Post;
import com.filrouge.designflow.spring.model.Subflow;
import com.filrouge.designflow.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubflow(Subflow subflow);

    List<Post> findByUser(User user);
}
