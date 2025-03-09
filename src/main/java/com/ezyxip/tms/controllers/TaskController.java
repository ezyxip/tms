package com.ezyxip.tms.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezyxip.tms.data.Task;
import com.ezyxip.tms.entities.UserEntity;
import com.ezyxip.tms.services.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, @AuthenticationPrincipal UserEntity user) {
        Task createdTask = taskService.createTask(task, user.getEmail());
        return ResponseEntity.ok(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task, @AuthenticationPrincipal UserEntity user) {
        Task updatedTask = taskService.updateTask(id, task, user.getEmail());
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id, @AuthenticationPrincipal UserEntity user) {
        taskService.deleteTask(id, user.getEmail());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/author")
    public ResponseEntity<List<Task>> getTasksByAuthor(@AuthenticationPrincipal UserEntity user) {
        List<Task> tasks = taskService.getTasksByAuthor(user.getEmail());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/assignee")
    public ResponseEntity<List<Task>> getTasksByAssignee(@AuthenticationPrincipal UserEntity user) {
        List<Task> tasks = taskService.getTasksByAssignee(user.getEmail());
        return ResponseEntity.ok(tasks);
    }
}
