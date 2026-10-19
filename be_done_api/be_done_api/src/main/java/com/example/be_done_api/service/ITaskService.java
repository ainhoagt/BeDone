package com.example.be_done_api.service;

import com.example.be_done_api.dto.CreateTaskRequest;
import com.example.be_done_api.dto.MoveTaskRequest;
import com.example.be_done_api.dto.TaskDTO;

public interface ITaskService {
    TaskDTO createTask(CreateTaskRequest taskRequest, Integer id);
    TaskDTO updateTask(TaskDTO taskDTO);
    void deleteTask(Integer id);
    TaskDTO moveTask(Integer taskId, MoveTaskRequest moveTaskRequest);
}
