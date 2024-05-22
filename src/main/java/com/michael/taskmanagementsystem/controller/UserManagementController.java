package com.michael.taskmanagementsystem.controller;

import com.michael.taskmanagementsystem.dto.ReqRes;
import com.michael.taskmanagementsystem.dto.TaskReqRes;
import com.michael.taskmanagementsystem.entity.OurUsers;
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
    private TaskService taskService;

    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> register(@RequestBody ReqRes reg) {
        return ResponseEntity.ok(usersManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req) {
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req) {
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/user/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers() {
        return ResponseEntity.ok(usersManagementService.getAllUsers());

    }

    @GetMapping("/user/get-users/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId) {
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));

    }

    @PutMapping("/user/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres) {
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/user/get-profile")
    public ResponseEntity<ReqRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = usersManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/user/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUSer(@PathVariable Integer userId) {
        return ResponseEntity.ok(usersManagementService.deleteUser(userId));
    }


//   ----------- End points for Tasks


    @PostMapping("/user/create-task/{userId}")
    public ResponseEntity<TaskReqRes> createTask(@PathVariable Integer userId, @RequestBody TaskReqRes taskReqRes) {
        return ResponseEntity.ok(taskService.createTask(userId, taskReqRes));
    }

    @PutMapping("/user/update-task/{userId}/{taskId}")
    public ResponseEntity<TaskReqRes> updateTask(@PathVariable Integer userId, @PathVariable Integer taskId, @RequestBody TaskReqRes taskReqRes) {
        return ResponseEntity.ok(taskService.updateTask(userId, taskId, taskReqRes));
    }

    @GetMapping("/user/get-tasks/{userId}")
    public ResponseEntity<List<TaskReqRes>> getTasksByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(taskService.getTasksByUserId(userId));
    }

    @GetMapping("/user/get-tasks/{userId}/{taskId}")
    public ResponseEntity<TaskReqRes> getSingleTaskByUserId(@PathVariable Integer userId, @PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.getSingleTask(userId, taskId));
    }

    @DeleteMapping("/user/delete-task/{userId}/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer userId, @PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.deleteTask(userId, taskId));
    }
}