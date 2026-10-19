package com.example.be_done_api.service;

import com.example.be_done_api.dto.BoardColumnDTO;
import com.example.be_done_api.exception.NotFound;
import com.example.be_done_api.mapper.Mapper;
import com.example.be_done_api.model.Board;
import com.example.be_done_api.model.BoardColumn;
import com.example.be_done_api.repository.BoardColumnRepository;
import com.example.be_done_api.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardColumnService implements IBoardColumnService {
    @Autowired
    private BoardColumnRepository boardColumnRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    @Override
    public BoardColumnDTO createBoardColumn(BoardColumnDTO boardColumnDTO, Integer id) {
        Board board=boardRepository.findById(id).orElse(null);
        if(board!=null){
            BoardColumn boardColumn= BoardColumn.builder()
                    .columnName(boardColumnDTO.getName())
                    .positionIndex(boardColumnDTO.getPositionIndex())
                    .colour(boardColumnDTO.getColour())
                    .board(board)
                    .positionIndex(boardColumnRepository.countByBoardId(id))
                    .build();
            return Mapper.toBoardColumnDTO(boardColumnRepository.save(boardColumn));
        }else{
            throw new NotFound("Board column with id "+boardColumnDTO.getId()+" not found");
        }
    }
    @Transactional
    @Override
    public BoardColumnDTO updateBoardColumn(BoardColumnDTO boardColumnDTO,Integer boardColumnId) {
        BoardColumn boardColumn=boardColumnRepository.findById(boardColumnId).orElse(null);
        if(boardColumn!=null){
            boardColumn.setColumnName(boardColumnDTO.getName());
            boardColumn.setPositionIndex(boardColumnDTO.getPositionIndex());
            boardColumn.setColour(boardColumnDTO.getColour());
            return Mapper.toBoardColumnDTO(boardColumn);
        }else{
            throw new NotFound("Board column with id "+boardColumnDTO.getId()+" not found");
        }

    }

    @Transactional
    @Override
    public void deleteBoardColumn(Integer id) {
        boardColumnRepository.deleteById(id);
    }

}
