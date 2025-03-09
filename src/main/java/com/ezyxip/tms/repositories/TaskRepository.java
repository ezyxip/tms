package com.ezyxip.tms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezyxip.tms.data.Task;
import com.ezyxip.tms.entities.UserEntity;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAuthor(UserEntity author);
    List<Task> findByAssignee(UserEntity assignee);
}