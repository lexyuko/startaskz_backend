package com.michael.taskmanagementsystem.controller;

import com.michael.taskmanagementsystem.dto.ReqRes;
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

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(usersManagementService.getAllUsers());

    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));

    }

    @PutMapping("/user /update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return  ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
    }


//   ----------- End points for Tasks

    @PostMapping("/user/create-task/{userId}")
    public ResponseEntity<Task> createTask(@PathVariable Long userId, @RequestBody Task task){
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @PutMapping("/user/update-task/{userId}/{taskName}")
    public ResponseEntity<Task> updateTask(@PathVariable Long userId, @PathVariable String taskName, @RequestBody Task task){
        return ResponseEntity.ok(taskService.updateTask(task,userId,taskName));
    }

    @GetMapping("/user/get-tasks/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(taskService.getTasksByUserId(userId));
    }

    @GetMapping("/user/get-tasks/{userId}/{taskName}")
    public ResponseEntity<Task> getSingleTaskByUserId(@PathVariable Long userId, @PathVariable String taskName){
        return ResponseEntity.ok(taskService.getSingleTask(userId,taskName));
    }

    @GetMapping("/user/delete-task/{userId}/{taskName}")
    public ResponseEntity<String> deleteTask(@PathVariable Long userId, @PathVariable String taskName){
        taskService.deleteTask(userId,taskName);
        return ResponseEntity.ok("task "+taskName+", deleted!");
    }




}
