package com.michael.taskmanagementsystem.repository;

import com.michael.taskmanagementsystem.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TaskRepo extends JpaRepository<Task, Integer> {
    public List<Task> findByUserId(Integer userId);
//    public void deleteByTaskName(String taskName);
}
