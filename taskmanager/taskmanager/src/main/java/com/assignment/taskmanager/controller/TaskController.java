package com.assignment.taskmanager.controller;

import com.assignment.taskmanager.dto.TaskDTO;
import com.assignment.taskmanager.entity.Task;
import com.assignment.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // CREATE a new task
    @PostMapping
    public Task createTask(@RequestBody TaskDTO dto, Authentication authentication) {
        String userEmail = authentication.getName();
        return taskService.createTask(dto, userEmail);
    }

    // READ all tasks for logged-in user
    @GetMapping
    public List<Task> getTasks(Authentication authentication) {
        String userEmail = authentication.getName();
        return taskService.getTasks(userEmail);
    }

    // READ single task by ID
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id, Authentication authentication) {
        String userEmail = authentication.getName();
        return taskService.getTaskById(id, userEmail);
    }

    // UPDATE task
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskDTO dto, Authentication authentication) {
        String userEmail = authentication.getName();
        return taskService.updateTask(id, dto, userEmail);
    }

    // DELETE task
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id, Authentication authentication) {
        String userEmail = authentication.getName();
        taskService.deleteTask(id, userEmail);
        return "Task deleted successfully";
    }
}