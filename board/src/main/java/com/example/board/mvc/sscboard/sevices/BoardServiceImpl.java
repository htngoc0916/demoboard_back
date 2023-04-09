package com.example.board.mvc.sscboard.sevices;

import com.example.board.mvc.sscboard.entity.Board;
import com.example.board.mvc.sscboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardServices{
    @Autowired
    private BoardRepository boardRepository;

    @Override
    public List<Board> findByTilteLike(String title) {
        return boardRepository.findByTitleLike(title);
    }
}
