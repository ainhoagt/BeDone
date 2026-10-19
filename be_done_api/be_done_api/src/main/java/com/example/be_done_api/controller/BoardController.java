package com.example.be_done_api.controller;

import com.example.be_done_api.dto.BoardDTO;
import com.example.be_done_api.dto.BoardSummaryDTO;
import com.example.be_done_api.service.IBoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/boards")
public class BoardController {
    @Autowired
    private IBoardService boardService;

    @GetMapping("/{id}")
    public ResponseEntity<BoardDTO> getBoard(@PathVariable Integer id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createBoard(@Valid @RequestBody BoardDTO boardDTO, @PathVariable Integer userId) {
        BoardSummaryDTO createdBoard = boardService.createBoard(boardDTO, userId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdBoard.getId()).toUri();
        return ResponseEntity.created(location).body(createdBoard);
    }
    @PostMapping("/{id}")
    public ResponseEntity<BoardSummaryDTO> updateBoard(@Valid @RequestBody BoardDTO boardDTO, @PathVariable Integer id) {
        return ResponseEntity.ok(boardService.updateBoard(boardDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Integer id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}