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
    public Task updateTask(Task task, Integer userId, Integer taskId){
        List<Task> listOfTask = taskRepo.findByUserId(userId);
        for(Task specificTask: listOfTask){
            if(specificTask.getId()==taskId){
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

    public List<Task> getTasksByUserId(Integer userId){
        return taskRepo.findByUserId(userId);
    }


    public Task getSingleTask(Integer userId, Integer taskId) {
        List<Task> taskList = getTasksByUserId(userId);
        for (Task task : taskList) {
            if (task.getId()==taskId) {
                return task;
            }
        }
        return null;
    }

    public String deleteTask(Integer userId, Integer taskId){
        List<Task> taskList = getTasksByUserId(userId);
        for (Task task: taskList){
            if(task.getId()==(taskId)){
                taskRepo.deleteById(task.getId());
            }
        }
        return "task "+ taskId+" deleted successfully";
    }
}
