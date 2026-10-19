package com.example.be_done_api.repository;

import com.example.be_done_api.model.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardColumnRepository extends JpaRepository<BoardColumn,Integer> {
    int countByBoardId(Integer boardId);
}
