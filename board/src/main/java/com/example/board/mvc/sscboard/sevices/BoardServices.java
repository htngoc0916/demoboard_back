package com.example.board.mvc.sscboard.sevices;

import com.example.board.mvc.sscboard.entity.Board;

import java.util.List;

public interface BoardServices {
    List<Board> findByTilteLike(String title);
}
