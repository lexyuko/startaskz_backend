package com.michael.taskmanagementsystem.service;

import com.michael.taskmanagementsystem.dto.ReqRes;
import com.michael.taskmanagementsystem.entity.Task;
import com.michael.taskmanagementsystem.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;
    @Autowired
    private JWTUtils jwtUtils;


    public ReqRes createTask(ReqRes createTaskRequest){
        ReqRes resp = new ReqRes();

        try{
            Task task = new Task();
            task.setTaskName(createTaskRequest.getTaskName());
            task.setTaskDescription(createTaskRequest.getTaskDescription());
            task.setCategory(createTaskRequest.getTask().getCategory());
            task.setStatus(createTaskRequest.getTask().getStatus());
            task.setStartDate(createTaskRequest.getStartDate());
            task.setEndDate(createTaskRequest.getEndDate());
            task.setStartTime(createTaskRequest.getStartTime());
            task.setEndTime(createTaskRequest.getEndTime());
            Task  taskResult = taskRepo.save(task);

            if (taskResult.getId()>0){
                resp.setTask((taskResult));
                resp.setMessage("Task Created Successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
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
//public TaskReqRes createTask(TaskReqRes taskReqRes) {
//    Task task = mapToTask(taskReqRes);
//    Task savedTask = taskRepo.save(task);
//    return mapToTaskReqRes(savedTask);
//}
//
//    public TaskReqRes updateTask(TaskReqRes taskReqRes, Integer userId, Integer taskId) {
//        Optional<Task> optionalTask = taskRepo.findById(taskId);
//        if (optionalTask.isPresent()) {
//            Task existingTask = optionalTask.get();
//            if (existingTask.getUserId().equals(userId)) {
//                existingTask = mapToTask(taskReqRes, existingTask);
//                existingTask.setId(taskId);
//                Task updatedTask = taskRepo.save(existingTask);
//                return mapToTaskReqRes(updatedTask);
//            } else {
//                // Task does not belong to the specified user
//                return null;
//            }
//        } else {
//            // Task not found
//            return null;
//        }
//    }

//    public List<TaskReqRes> getTasksByUserId(Integer userId) {
//        List<Task> tasks = taskRepo.findByUserId(userId);
//        return tasks.stream()
//                .map(this::mapToTaskReqRes)
//                .collect(Collectors.toList());
//    }
//public List<Task> getTasksByUserId(Integer userId){
//    return taskRepo.findByUserId(userId);
//}
//
//    public TaskReqRes getSingleTask(Integer userId, Integer taskId) {
//        Optional<Task> optionalTask = taskRepo.findById(taskId);
//        if (optionalTask.isPresent()) {
//            Task task = optionalTask.get();
//            if (task.getUserId().equals(userId)) {
//                return mapToTaskReqRes(task);
//            } else {
//                // Task does not belong to the specified user
//                return null;
//            }
//        } else {
//            // Task not found
//            return null;
//        }
//    }
//
//    public String deleteTask(Integer userId, Integer taskId) {
//        Optional<Task> optionalTask = taskRepo.findById(taskId);
//        if (optionalTask.isPresent()) {
//            Task task = optionalTask.get();
//            if (task.getUserId().equals(userId)) {
//                taskRepo.deleteById(taskId);
//                return "Task " + taskId + " deleted successfully";
//            } else {
//                // Task does not belong to the specified user
//                return "Task " + taskId + " does not belong to the specified user";
//            }
//        } else {
//            // Task not found
//            return "Task " + taskId + " not found";
//        }
//    }
//
//    // Helper method to map TaskReqRes to Task
//    private Task mapToTask(TaskReqRes taskReqRes) {
//        Task task = new Task();
//        task.setTaskName(taskReqRes.getTaskName());
//        task.setTaskDescription(taskReqRes.getTaskDescription());
//        // Set other fields similarly
//        return task;
//    }
//
//    // Helper method to map Task to TaskReqRes
//    private TaskReqRes mapToTaskReqRes(Task task) {
//        TaskReqRes taskReqRes = new TaskReqRes();
//        taskReqRes.setId(task.getId());
//        taskReqRes.setTaskName(task.getTaskName());
//        taskReqRes.setTaskDescription(task.getTaskDescription());
//        // Set other fields similarly
//        return taskReqRes;
//    }
//
//    // Helper method to update existing Task with TaskReqRes data
//    private Task mapToTask(TaskReqRes taskReqRes, Task existingTask) {
//        existingTask.setTaskName(taskReqRes.getTaskName());
//        existingTask.setTaskDescription(taskReqRes.getTaskDescription());
//        // Update other fields similarly
//        return existingTask;
//    }
//}