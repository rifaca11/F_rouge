package com.filrouge.designflow.spring.repository;

import com.filrouge.designflow.spring.model.Comment;
import com.filrouge.designflow.spring.model.Post;
import com.filrouge.designflow.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
