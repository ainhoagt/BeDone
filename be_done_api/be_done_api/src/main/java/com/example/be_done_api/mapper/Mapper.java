package com.example.be_done_api.mapper;

import com.example.be_done_api.dto.*;
import com.example.be_done_api.model.Board;
import com.example.be_done_api.model.BoardColumn;
import com.example.be_done_api.model.Task;
import com.example.be_done_api.model.User;
import com.example.be_done_api.utils.PasswordUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
   public static UserDTO toUserDTO(User user){
       List<BoardSummaryDTO> boardsDTO=user.getBoards().stream()
               .map(Mapper::toBoardSummaryDTO)
               .collect(Collectors.toList());

       return UserDTO.builder()
               .id(user.getId())
               .name(user.getUserName())
               .email(user.getEmail())
               .boards(boardsDTO)
               .createdAt(user.getCreatedAt())
               .build();
   }
public static BoardSummaryDTO toBoardSummaryDTO(Board b) {
    return BoardSummaryDTO.builder()
            .id(b.getId())
            .name(b.getBoardName())
            .colour(b.getColour())
            .backgroundImage(b.getBackgroundImage())
            .build();
}
    public static BoardDTO toBoardDTO(Board b) {

       return BoardDTO.builder()
                .id(b.getId())
                .name(b.getBoardName())
                .colour(b.getColour())
                .backgroundImage(b.getBackgroundImage())
                .columns(b.getBoardColumns().stream()
                        .map(Mapper::toBoardColumnDTO)
                        .collect(Collectors.toList()))
                .build();
    }


    public static BoardColumnDTO toBoardColumnDTO(BoardColumn c) {
        List<TaskDTO> tasks=c.getTasks().stream()
                .map(Mapper::toTaskDTO)
                .collect(Collectors.toList());
        return BoardColumnDTO.builder()
                .id(c.getId())
                .name(c.getColumnName())
                .colour(c.getColour())
                .positionIndex(c.getPositionIndex())
                .tasks(tasks)
                .build();
    }
    public static TaskDTO toTaskDTO(Task task){
       return TaskDTO.builder()
               .id(task.getId())
               .name(task.getTaskName())
               .description(task.getDescription())
               .colour(task.getColour())
               .positionIndex(task.getPositionIndex())
               .build();
    }
    public static User toEntity(CreateRequestUser requestUser){
       return User.builder()
                .userName(requestUser.getName())
                .email(requestUser.getEmail())
                .passwordHash(PasswordUtils.hashPassword(requestUser.getPassword()))
                .build();
    }

}
