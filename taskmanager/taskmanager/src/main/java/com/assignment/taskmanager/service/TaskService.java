package com.assignment.taskmanager.service;

import com.assignment.taskmanager.dto.TaskDTO;
import com.assignment.taskmanager.entity.Task;
import com.assignment.taskmanager.entity.User;
import com.assignment.taskmanager.repository.TaskRepository;
import com.assignment.taskmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    // CREATE
    public Task createTask(TaskDTO dto, String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));

        Task task = Task.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .user(user)
                .build();

        return taskRepository.save(task);
    }

    // READ all tasks
    public List<Task> getTasks(String userEmail) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        return taskRepository.findByUserId(user.getId());
    }

    // READ single task
    public Task getTaskById(Long taskId, String userEmail) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("You do not have permission to view this task");
        }
        return task;
    }

    // UPDATE
    public Task updateTask(Long taskId, TaskDTO dto, String userEmail) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("You do not have permission to update this task");
        }

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());

        return taskRepository.save(task);
    }

    // DELETE
    public void deleteTask(Long taskId, String userEmail) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));

        if (!task.getUser().getEmail().equals(userEmail)) {
            throw new RuntimeException("You do not have permission to delete this task");
        }

        taskRepository.delete(task);
    }
}
