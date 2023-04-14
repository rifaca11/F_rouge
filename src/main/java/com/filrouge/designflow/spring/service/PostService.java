package com.filrouge.designflow.spring.service;

import com.filrouge.designflow.spring.dto.PostRequest;
import com.filrouge.designflow.spring.dto.PostResponse;
import com.filrouge.designflow.spring.model.Post;
import com.filrouge.designflow.spring.model.Subflow;
import com.filrouge.designflow.spring.model.User;
import com.filrouge.designflow.spring.exceptions.PostNotFoundException;
import com.filrouge.designflow.spring.exceptions.SubflowNotFoundException;
import com.filrouge.designflow.spring.mapper.PostMapper;
import com.filrouge.designflow.spring.repository.PostRepository;
import com.filrouge.designflow.spring.repository.SubflowRepository;
import com.filrouge.designflow.spring.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubflowRepository subflowRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

//    Method for saving post
    public void save(PostRequest postRequest) {
        Subflow subflow = subflowRepository.findByName(postRequest.getSubflowName())
                .orElseThrow(() -> new SubflowNotFoundException(postRequest.getSubflowName()));
        postRepository.save(postMapper.map(postRequest, subflow, authService.getCurrentUser()));
    }

//    Method for getting post
    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

//    Method for getting all posts
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

//    Method for getting posts by subdesignflow
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubflow(Long subflowId) {
        Subflow subflow = subflowRepository.findById(subflowId)
                .orElseThrow(() -> new SubflowNotFoundException(subflowId.toString()));
        List<Post> posts = postRepository.findAllBySubflow(subflow);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

//    Method for getting posts by username
    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
