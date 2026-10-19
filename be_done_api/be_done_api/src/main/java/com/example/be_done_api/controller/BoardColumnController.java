package com.example.be_done_api.controller;

import com.example.be_done_api.dto.BoardColumnDTO;
import com.example.be_done_api.service.IBoardColumnService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/board-columns")
public class BoardColumnController {
    @Autowired
    private IBoardColumnService boardColumnService;

    @PostMapping("/board/{boardId}")
    public ResponseEntity<BoardColumnDTO> createBoardColumn(@Valid @RequestBody BoardColumnDTO boardColumnDTO, @PathVariable Integer boardId) {
        BoardColumnDTO createdBoardColumn = boardColumnService.createBoardColumn(boardColumnDTO, boardId);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdBoardColumn.getId()).toUri();
        return ResponseEntity.created(location).body(createdBoardColumn);
    }
    @PutMapping("/{boardColumnId}")
    public ResponseEntity<BoardColumnDTO> updateBoardColumn(@Valid @RequestBody BoardColumnDTO boardColumnDTO, @PathVariable Integer boardColumnId) {
        BoardColumnDTO updatedBoardColumn = boardColumnService.updateBoardColumn(boardColumnDTO, boardColumnId);
        return ResponseEntity.ok(updatedBoardColumn);
    }
    @DeleteMapping("/{boardColumnId}")
    public ResponseEntity<Void> deleteBoardColumn(@PathVariable Integer boardColumnId) {
        boardColumnService.deleteBoardColumn(boardColumnId);
        return ResponseEntity.noContent().build();
    }
}
