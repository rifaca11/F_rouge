package com.filrouge.designflow.spring.controller;

import com.filrouge.designflow.spring.dto.PostRequest;
import com.filrouge.designflow.spring.dto.PostResponse;
import com.filrouge.designflow.spring.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@CrossOrigin
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

//    Method for creating new post
    @PostMapping
    public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    Method for getting all posts

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return status(HttpStatus.OK).body(postService.getAllPosts());
    }

//  Method for getting one post by its id
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return status(HttpStatus.OK).body(postService.getPost(id));
    }

//  Method for getting posts by subflow
    @GetMapping(params = "subflowId")
    public ResponseEntity<List<PostResponse>> getPostsBySubflow(@RequestParam Long subflow) {
        return status(HttpStatus.OK).body(postService.getPostsBySubflow(subflow));
    }

//    Method for getting posts by username
    @GetMapping(params = "username")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@RequestParam String username) {
        return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
    }
}
