package com.filrouge.designflow.spring.service;

import com.filrouge.designflow.spring.dto.CommentsDto;
import com.filrouge.designflow.spring.model.Comment;
import com.filrouge.designflow.spring.model.NotificationEmail;
import com.filrouge.designflow.spring.model.Post;
import com.filrouge.designflow.spring.model.User;
import com.filrouge.designflow.spring.exceptions.PostNotFoundException;
import com.filrouge.designflow.spring.exceptions.SpringFlowException;
import com.filrouge.designflow.spring.mapper.CommentMapper;
import com.filrouge.designflow.spring.repository.CommentRepository;
import com.filrouge.designflow.spring.repository.PostRepository;
import com.filrouge.designflow.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    //    Method for creating comment
    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() + " posted a comment on your post." + POST_URL);
//        sendCommentNotification(message, post.getUser());
    }

//    Method for sending comment notification
//    private void sendCommentNotification(String message, User user) {
//        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
//    }

//    Method for getting all comments for post
    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto).toList();
    }


//    Method for getting all comments for user
    public List<CommentsDto> getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .toList();
    }

    public boolean containsSwearWords(String comment) {
        if (comment.contains("shit")) {
            throw new SpringFlowException("Comments contains unacceptable language");
        }
        return false;
    }
}
