package com.michael.taskmanagementsystem.service;

import com.michael.taskmanagementsystem.dto.TaskReqRes;
import com.michael.taskmanagementsystem.entity.Task;
import com.michael.taskmanagementsystem.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepo taskRepo;

    public TaskReqRes createTask(Integer userId, TaskReqRes taskReqRes) {
        Task task = new Task();
        task.setUserId(userId);
        task.setTaskName(taskReqRes.getTaskName());
        task.setTaskDescription(taskReqRes.getTaskDescription());
        task.setStartDate(taskReqRes.getStartDate());
        task.setEndDate(taskReqRes.getEndDate());
        task.setStartTime(taskReqRes.getStartTime());
        task.setEndTime(taskReqRes.getEndTime());
        task.setStatus(taskReqRes.getStatus());
        task.setCategory(taskReqRes.getCategory());
        Task savedTask = taskRepo.save(task);
        taskReqRes.setId(savedTask.getId());
        return taskReqRes;
    }

    public TaskReqRes updateTask(Integer userId, Integer taskId, TaskReqRes taskReqRes) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getUserId().equals(userId)) {
            task.setTaskName(taskReqRes.getTaskName());
            task.setTaskDescription(taskReqRes.getTaskDescription());
            task.setStartDate(taskReqRes.getStartDate());
            task.setEndDate(taskReqRes.getEndDate());
            task.setStartTime(taskReqRes.getStartTime());
            task.setEndTime(taskReqRes.getEndTime());
            task.setStatus(taskReqRes.getStatus());
            task.setCategory(taskReqRes.getCategory());
            taskRepo.save(task);
            taskReqRes.setId(task.getId());
        } else {
            throw new RuntimeException("Unauthorized action");
        }
        return taskReqRes;
    }

    public List<TaskReqRes> getTasksByUserId(Integer userId) {
        List<Task> tasks = taskRepo.findByUserId(userId);
        return tasks.stream().map(task -> {
            TaskReqRes taskReqRes = new TaskReqRes();
            taskReqRes.setId(task.getId());
            taskReqRes.setTaskName(task.getTaskName());
            taskReqRes.setTaskDescription(task.getTaskDescription());
            taskReqRes.setStartDate(task.getStartDate());
            taskReqRes.setEndDate(task.getEndDate());
            taskReqRes.setStartTime(task.getStartTime());
            taskReqRes.setEndTime(task.getEndTime());
            taskReqRes.setStatus(task.getStatus());
            taskReqRes.setCategory(task.getCategory());
            return taskReqRes;
        }).collect(Collectors.toList());
    }

    public TaskReqRes getSingleTask(Integer userId, Integer taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getUserId().equals(userId)) {
            TaskReqRes taskReqRes = new TaskReqRes();
            taskReqRes.setId(task.getId());
            taskReqRes.setTaskName(task.getTaskName());
            taskReqRes.setTaskDescription(task.getTaskDescription());
            taskReqRes.setStartDate(task.getStartDate());
            taskReqRes.setEndDate(task.getEndDate());
            taskReqRes.setStartTime(task.getStartTime());
            taskReqRes.setEndTime(task.getEndTime());
            taskReqRes.setStatus(task.getStatus());
            taskReqRes.setCategory(task.getCategory());
            return taskReqRes;
        } else {
            throw new RuntimeException("Unauthorized action");
        }
    }

    public String deleteTask(Integer userId, Integer taskId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        if (task.getUserId().equals(userId)) {
            taskRepo.deleteById(taskId);
            return "Task " + taskId + " deleted successfully";
        } else {
            throw new RuntimeException("Unauthorized action");
        }
    }
}
