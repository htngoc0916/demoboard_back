package com.example.board.mvc.sscboard.repository;

import com.example.board.mvc.sscboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //check title when insert
    List<Board> findByTitle(String title);
}
