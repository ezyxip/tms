package com.ezyxip.tms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezyxip.tms.data.Comment;
import com.ezyxip.tms.data.Task;
import com.ezyxip.tms.entities.UserEntity;
import com.ezyxip.tms.repositories.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final TaskService taskService;
    private final UserService userService;

    public Comment addComment(Long taskId, String text, String authorEmail) {
        Task task = taskService.getTaskById(taskId);
        UserEntity author = userService.findByEmail(authorEmail);

        Comment comment = new Comment();
        comment.setText(text);
        comment.setTask(task);
        comment.setAuthor(author);

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByTask(Long taskId) {
        Task task = taskService.getTaskById(taskId);
        return commentRepository.findByTask(task);
    }
}
