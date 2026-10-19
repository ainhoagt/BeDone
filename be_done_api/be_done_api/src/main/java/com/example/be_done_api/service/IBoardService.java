package com.example.be_done_api.service;

import com.example.be_done_api.dto.BoardDTO;
import com.example.be_done_api.dto.BoardSummaryDTO;
import com.example.be_done_api.dto.UserDTO;
import com.example.be_done_api.model.Board;

import java.util.List;

public interface IBoardService {
    BoardDTO getBoardById(Integer boardId);
    BoardSummaryDTO createBoard(BoardDTO boardDTO, Integer id);
    BoardSummaryDTO updateBoard(BoardDTO boardDTO);
    void deleteBoard(Integer id);
}
