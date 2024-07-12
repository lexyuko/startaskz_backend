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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@RestController
public class UserManagementController {

    private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);
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

    @GetMapping("/admin/get-all-users")
    public ResponseEntity<ReqRes> getAllUsers() {
        logger.info("Get all users request received");
        return ResponseEntity.ok(usersManagementService.getAllUsers());
    }

    @GetMapping("/admin/get-user/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId) {
        logger.info("Get all users request received");
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));

    }

    @PutMapping("/adminuser/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres) {
        logger.info("Get all users request received");
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));

    }

    @GetMapping("/adminuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        logger.info("Get all users request received");
        ReqRes response = usersManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @DeleteMapping("/adminuser/delete/{userId}")
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

    @GetMapping("/user/get-task/{userId}/{taskId}")
    public ResponseEntity<TaskReqRes> getSingleTaskByUserId(@PathVariable Integer userId, @PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.getSingleTask(userId, taskId));
    }

    @DeleteMapping("/user/delete-task/{userId}/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Integer userId, @PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.deleteTask(userId, taskId));
    }
}