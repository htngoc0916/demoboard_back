package com.example.board.mvc.sscboard.repository;

import com.example.board.mvc.sscboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    //check title when insert
    List<Board> findByTitle(String title);
    List<Board> findAllByOrderByIdDesc();

    @Query(value = "SELECT * FROM BOARD_POST WHERE REPLACE(TRIM(TITLE), ' ', '')  COLLATE utf8_general_ci LIKE CONCAT('%',:title,'%') ORDER BY CREATED_TIME DESC", nativeQuery = true)
    List<Board> findByTitleLike(@Param("title") String title);

}
