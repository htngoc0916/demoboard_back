package com.example.board.mvc.sscboard.controllers;

import com.example.board.mvc.sscboard.entity.Board;
import com.example.board.mvc.sscboard.entity.ResponseObject;
import com.example.board.mvc.sscboard.repository.BoardRepository;
import com.example.board.mvc.sscboard.sevices.BoardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


//thiet lap cho phep no la mot controller
@RestController
@RequestMapping("/api/boards")
public class BoardController {
    @Autowired
    private BoardRepository repository;
    @Autowired
    private BoardServices boardServices;

    //search all
    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<ResponseObject> findAll(){
        List<Board> foundBoard = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Query board successfully", foundBoard)
        );
    }

    //search with ID
    @GetMapping("")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<ResponseObject> findById(@RequestParam Long id){
        //optional co the tra ve ket qua sau khi tim kiem bang ham findById
        Optional<Board> foundBoard = repository.findById(id);
        //check xem no khac null hay khong
        if(foundBoard.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query board successfully", foundBoard)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Cannot find board with id = " + id, "")
            );
        }
    }

    //search with query
    @GetMapping("/{query}")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<ResponseObject> searchBoards(@PathVariable String query){

        //optional co the tra ve ket qua sau khi tim kiem bang ham findById
        List<Board> foundBoard = boardServices.findByTilteLike(query.replace(" ",""));

        //check xem no khac null hay khong
        if(foundBoard.size() > 0){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Query board successfully", foundBoard)
            );
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Cannot find board with title = " + query, "")
            );
        }
    }

    //insert
    @PostMapping("/add")
    @CrossOrigin(origins = "http://localhost:3000")
    //Postman : raw, json
    ResponseEntity<ResponseObject> insertBoard(@RequestBody Board newBoard){
        //tim cac bai viet da co title nhu the nay
        List<Board> foundBoards = repository.findByTitle(newBoard.getTitle().trim());
        if(foundBoards.size() > 0){
            //ham repository.save(newBoard) de luu
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Insert Board already taken", "")
            );
        }

        //ham repository.save(newBoard) de luu
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Board  successfully",repository.save(newBoard))
        );
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<ResponseObject> updateBoard(@RequestBody Board newBoard, @PathVariable Long id){
        try{
            Board updatedBoard = repository.findById(id).map(board->{
                board.setTitle(newBoard.getTitle());
                board.setContent(newBoard.getContent());
                board.setAuthor(newBoard.getAuthor());
                board.setCreatedUserID(newBoard.getCreatedUserID());
                board.setCreatedTime(newBoard.getCreatedTime());
                board.setUpdatedUserID(newBoard.getUpdatedUserID());
                board.setUpdatedTime(newBoard.getUpdatedTime());
                return repository.save(board);
            }).orElseGet(()->{
                return repository.save(newBoard);
            });

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Update Board successfully", updatedBoard)
            );
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("failed", "Update Board failed",  ex)
            );
        }
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        boolean exists = repository.existsById(id);
        if(exists){
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete board successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find board to delete", "")
        );
    }
}
