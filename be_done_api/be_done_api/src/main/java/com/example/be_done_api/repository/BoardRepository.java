package com.example.be_done_api.repository;

import com.example.be_done_api.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Integer> {
}
