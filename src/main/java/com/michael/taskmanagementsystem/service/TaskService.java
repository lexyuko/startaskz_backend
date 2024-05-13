package com.michael.taskmanagementsystem.service;

import com.michael.taskmanagementsystem.entity.Task;
import com.michael.taskmanagementsystem.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;


    public Task createTask(Task task){
        return taskRepo.save(task);
    }
    public Task updateTask(Task task, Long userId, String taskName){
        List<Task> listOfTask = taskRepo.findByUserId(userId);
        for(Task specificTask: listOfTask){
            if(specificTask.getTaskName().equals(taskName)){
                specificTask.setTaskName(task.getTaskName());
                specificTask.setTaskDescription(task.getTaskDescription());
                specificTask.setCategory(task.getCategory());
                specificTask.setEndDate(task.getEndDate());
                specificTask.setEndTime(task.getEndTime());
                return specificTask;
            }
        }
        return null;
    }

    public List<Task> getTasksByUserId(Long userId){
        return taskRepo.findByUserId(userId);
    }


    public Task getSingleTask(Long userId, String taskName) {
        List<Task> taskList = getTasksByUserId(userId);
        for (Task task : taskList) {
            if (task.getTaskName().equals(taskName)) {
                return task;
            }
        }
        return null;
    }

    public String deleteTask(Long userId, String taskName){
        List<Task> taskList = getTasksByUserId(userId);
        for (Task task: taskList){
            if(task.getTaskName().equals(taskName)){
                taskRepo.deleteByTaskName(task.getTaskName());
            }
        }
        return "task "+ taskName+" deleted successfully";
    }
}
