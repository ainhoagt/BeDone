package com.example.be_done_api.controller;

import com.example.be_done_api.dto.CreateTaskRequest;
import com.example.be_done_api.dto.TaskDTO;
import com.example.be_done_api.service.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private ITaskService taskService;

    @PostMapping("/column/{columnId}")
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody CreateTaskRequest requestTask, @PathVariable Integer columnId){
        TaskDTO createdTask=taskService.createTask(requestTask,columnId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdTask);
    }
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }
}
