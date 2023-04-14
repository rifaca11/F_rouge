package com.filrouge.designflow.spring.controller;

import com.filrouge.designflow.spring.dto.SubflowDto;
import com.filrouge.designflow.spring.service.SubflowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subdesignflow")
@AllArgsConstructor
@Slf4j
public class SubflowController {

    private final SubflowService subflowService;

//  Method for creating subflow
    @PostMapping
    public ResponseEntity<SubflowDto> createSubflow(@RequestBody SubflowDto subflowDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subflowService.save(subflowDto));
    }

//  Method for getting all subreddits
    @GetMapping
    public ResponseEntity<List<SubflowDto>> getAllSubflows() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subflowService.getAll());
    }

//   Method for getting one subflow by id
    @GetMapping("/{id}")
    public ResponseEntity<SubflowDto> getSubflow(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subflowService.getSubflow(id));
    }
}
