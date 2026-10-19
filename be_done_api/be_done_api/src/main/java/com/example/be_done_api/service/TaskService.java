package com.example.be_done_api.service;

import com.example.be_done_api.dto.CreateTaskRequest;
import com.example.be_done_api.dto.MoveTaskRequest;
import com.example.be_done_api.dto.TaskDTO;
import com.example.be_done_api.exception.NotFound;
import com.example.be_done_api.mapper.Mapper;
import com.example.be_done_api.model.BoardColumn;
import com.example.be_done_api.model.Task;
import com.example.be_done_api.repository.BoardColumnRepository;
import com.example.be_done_api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService implements ITaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private BoardColumnRepository boardColumnRepository;
    @Transactional
    @Override
    public TaskDTO createTask(CreateTaskRequest taskRequest, Integer columnId) {
        BoardColumn boardColumn=boardColumnRepository.findById(columnId).orElse(null);
        if(boardColumn!=null){
            Task task= Task.builder()
                    .taskName(taskRequest.getName())
                    .description(taskRequest.getDescription())
                    .positionIndex(taskRepository.countByBoardColumnId(columnId))
                    .boardColumn(boardColumn)
                    .colour(taskRequest.getColour())
                    .build();
            return Mapper.toTaskDTO(taskRepository.save(task));
        }else{
            throw new NotFound("Board column with id "+columnId+" not found");
        }

    }
    @Transactional
    @Override
    public TaskDTO updateTask(TaskDTO taskDTO) {
        Task task=taskRepository.findById(taskDTO.getId()).orElse(null);
        if(task!=null){
            task.setTaskName(taskDTO.getName());
            task.setDescription(taskDTO.getDescription());
            task.setPositionIndex(taskDTO.getPositionIndex());
            return Mapper.toTaskDTO(task);
        }else{
            throw new NotFound("Task with id "+taskDTO.getId()+" not found");
        }
    }
    @Transactional
    @Override
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
    @Transactional
    @Override
    public TaskDTO moveTask(Integer taskId, MoveTaskRequest moveTaskRequest) {
        Task task=taskRepository.findById(taskId).orElse(null);
        if(task!=null){
            //columna de destino
            BoardColumn boardColumn=boardColumnRepository.findById(moveTaskRequest.getTargetColumnId()).orElse(null);
            if(boardColumn!=null){
                Integer columnSource=task.getBoardColumn().getId();//id de columna de inicio
                int oldPos=task.getPositionIndex();
                int newPos=moveTaskRequest.getTargetPosition();
                taskRepository.shiftDown(columnSource,oldPos);
                taskRepository.shiftUp(boardColumn.getId(),newPos);
                task.setBoardColumn(boardColumn);
                task.setPositionIndex(newPos);
                return Mapper.toTaskDTO(task);
            }else{
                throw new NotFound("Board column with id "+moveTaskRequest.getTargetColumnId()+" not found");
            }
        }
        return null;
    }
}
