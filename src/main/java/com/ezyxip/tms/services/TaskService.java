package com.ezyxip.tms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ezyxip.tms.data.Role;
import com.ezyxip.tms.data.Task;
import com.ezyxip.tms.entities.UserEntity;
import com.ezyxip.tms.repositories.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public Task createTask(Task task, String authorEmail) {
        UserEntity author = userService.findByEmail(authorEmail);
        task.setAuthor(author);
        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, Task updatedTask, String userEmail) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        UserEntity user = userService.findByEmail(userEmail);

        if (task.getAuthor().equals(user) || user.getRole() == Role.ADMIN) {
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setStatus(updatedTask.getStatus());
            task.setPriority(updatedTask.getPriority());
            task.setAssignee(updatedTask.getAssignee());
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Unauthorized");
        }
    }

    public void deleteTask(Long taskId, String userEmail) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        UserEntity user = userService.findByEmail(userEmail);

        if (task.getAuthor().equals(user) || user.getRole() == Role.ADMIN) {
            taskRepository.delete(task);
        } else {
            throw new RuntimeException("Unauthorized");
        }
    }

    public List<Task> getTasksByAuthor(String authorEmail) {
        UserEntity author = userService.findByEmail(authorEmail);
        return taskRepository.findByAuthor(author);
    }

    public List<Task> getTasksByAssignee(String assigneeEmail) {
        UserEntity assignee = userService.findByEmail(assigneeEmail);
        return taskRepository.findByAssignee(assignee);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
    }
}