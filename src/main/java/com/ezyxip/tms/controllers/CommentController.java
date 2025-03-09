package com.ezyxip.tms.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezyxip.tms.data.Comment;
import com.ezyxip.tms.entities.UserEntity;
import com.ezyxip.tms.services.CommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{taskId}")
    public ResponseEntity<Comment> addComment(@PathVariable Long taskId, @RequestBody String text, @AuthenticationPrincipal UserEntity user) {
        Comment comment = commentService.addComment(taskId, text, user.getEmail());
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<List<Comment>> getCommentsByTask(@PathVariable Long taskId) {
        List<Comment> comments = commentService.getCommentsByTask(taskId);
        return ResponseEntity.ok(comments);
    }
}
