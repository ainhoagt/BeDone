package com.example.be_done_api.service;

import com.example.be_done_api.dto.BoardDTO;
import com.example.be_done_api.dto.BoardSummaryDTO;
import com.example.be_done_api.exception.NotFound;
import com.example.be_done_api.mapper.Mapper;
import com.example.be_done_api.model.Board;
import com.example.be_done_api.model.User;
import com.example.be_done_api.repository.BoardRepository;
import com.example.be_done_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService implements IBoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserRepository userRepository;
    @Transactional(readOnly = true)
    @Override
    public BoardDTO getBoardById(Integer boardId) {
        return boardRepository.findById(boardId)
                .stream()
                .map(Mapper::toBoardDTO)
                .findFirst()
                .orElse(null);
    }
    @Transactional
    @Override
    public BoardSummaryDTO createBoard(BoardDTO boardDTO, Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if(user==null){
            throw new NotFound("User with id "+userId+" not found");
        }
        Board board= Board.builder()
                .boardName(boardDTO.getName())
                .backgroundImage(boardDTO.getBackgroundImage())
                .colour(boardDTO.getColour())
                .user(user)
                .build();
        return Mapper.toBoardSummaryDTO(boardRepository.save(board));
    }
    @Transactional
    @Override
    public BoardSummaryDTO updateBoard(BoardDTO boardDTO){
        Board board=boardRepository.findById(boardDTO.getId()).orElse(null);
        if(board!=null){
            board.setBoardName(boardDTO.getName());
            board.setBackgroundImage(boardDTO.getBackgroundImage());
            board.setColour(boardDTO.getColour());
            return Mapper.toBoardSummaryDTO(board);
        }
        throw new NotFound("Board with id "+boardDTO.getId()+" not found");
    }

    @Transactional
    @Override
    public void deleteBoard(Integer id) {
        boardRepository.deleteById(id);
    }
}
