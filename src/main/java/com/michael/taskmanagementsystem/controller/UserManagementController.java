package com.michael.taskmanagementsystem.controller;

import com.michael.taskmanagementsystem.dto.ReqRes;
import com.michael.taskmanagementsystem.dto.TaskReqRes;
import com.michael.taskmanagementsystem.entity.OurUsers;
import com.michael.taskmanagementsystem.entity.Task;
import com.michael.taskmanagementsystem.service.TaskService;
import com.michael.taskmanagementsystem.service.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserManagementController {
    @Autowired
    private UsersManagementService usersManagementService;

    @Autowired
    TaskService taskService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg){
        return ResponseEntity.ok(usersManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/user/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(usersManagementService.getAllUsers());

    }

    @GetMapping("/user/get-users/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));

    }

    @PutMapping("/user/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/user/get-profile")
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
    }


//   ----------- End points for Tasks

//    @PostMapping("/user/create-task/{userId}")
//    public ResponseEntity<Task> createTask(@PathVariable Integer userId, @RequestBody Task task){
//        return ResponseEntity.ok(taskService.createTask(task));
//    }
//
//    @PutMapping("/user/update-task/{userId}/{taskId}")
//    public ResponseEntity<Task> updateTask(@PathVariable Integer userId, @PathVariable Integer taskId, @RequestBody Task task){
//        return ResponseEntity.ok(taskService.updateTask(task,userId,taskId));
//    }
//
//    @GetMapping("/user/get-tasks/{userId}")
//    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Integer userId){
//        return ResponseEntity.ok(taskService.getTasksByUserId(userId));
//    }
//
//    @GetMapping("/user/get-tasks/{userId}/{taskId}")
//    public ResponseEntity<Task> getSingleTaskByUserId(@PathVariable Integer userId, @PathVariable Integer taskId){
//        return ResponseEntity.ok(taskService.getSingleTask(userId,taskId));
//    }
//
//    @GetMapping("/user/delete-task/{userId}/{taskId}")
//    public ResponseEntity<String> deleteTask(@PathVariable Integer userId, @PathVariable Integer taskId){
//        taskService.deleteTask(userId,taskId);
//        return ResponseEntity.ok("task "+taskId+", deleted!");
//    }


    @PostMapping("/create-task/{userId}")
    public ResponseEntity<TaskReqRes> createTask(@PathVariable Integer userId, @RequestBody TaskReqRes taskReqRes) {
        TaskReqRes createdTask = taskService.createTask(taskReqRes);
        if (createdTask != null) {
            return ResponseEntity.ok(createdTask);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/update-task/{userId}/{taskId}")
    public ResponseEntity<TaskReqRes> updateTask(@PathVariable Integer userId, @PathVariable Integer taskId, @RequestBody TaskReqRes taskReqRes) {
        TaskReqRes updatedTask = taskService.updateTask(taskReqRes, userId, taskId);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/get-tasks/{userId}")
//    public ResponseEntity<List<TaskReqRes>> getTasksByUserId(@PathVariable Integer userId) {
//        List<TaskReqRes> tasks = taskService.getTasksByUserId(userId);
//        return ResponseEntity.ok(tasks);
//    }
@GetMapping("/user/get-tasks/{userId}")
public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Integer userId){
    return ResponseEntity.ok(taskService.getTasksByUserId(userId));
}

    @GetMapping("/get-task/{userId}/{taskId}")
    public ResponseEntity<TaskReqRes> getSingleTaskByUserId(@PathVariable Integer userId, @PathVariable Integer taskId) {
        TaskReqRes task = taskService.getSingleTask(userId, taskId);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-task/{userId}/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer userId, @PathVariable Integer taskId) {
        String message = taskService.deleteTask(userId, taskId);
        return ResponseEntity.ok(message);
    }


}
