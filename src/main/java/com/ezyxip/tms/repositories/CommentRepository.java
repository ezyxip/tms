package com.ezyxip.tms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ezyxip.tms.data.Comment;
import com.ezyxip.tms.data.Task;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTask(Task task);
}
