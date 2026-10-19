package com.example.be_done_api.service;

import com.example.be_done_api.dto.BoardColumnDTO;

import java.util.List;

public interface IBoardColumnService {
    BoardColumnDTO createBoardColumn(BoardColumnDTO boardColumnDTO, Integer id);
    BoardColumnDTO updateBoardColumn(BoardColumnDTO boardColumnDTO,Integer id);
    void deleteBoardColumn(Integer id);
}
