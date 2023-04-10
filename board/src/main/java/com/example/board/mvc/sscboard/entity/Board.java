package com.example.board.mvc.sscboard.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "BOARD_POST")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="TITLE", nullable = false, unique = true, length = 255)
    private String title;
    @Column(name="CONTENT")
    private String content;
    @Column(name="AUTHOR")
    private String author;
    @Column(name="CREATED_TIME")
    private Date createdTime;
    @Column(name="CREATED_USERID")
    private String createdUserID;
    @Column(name="UPDATED_TIME")
    private Date updatedTime;
    @Column(name="UPDATED_USERID")
    private String updatedUserID;


    public Board(){};

    public Board(String title, String content, String author, Date createdTime, String createdUserID, Date updatedTime, String updatedUserID) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdTime = createdTime;
        this.createdUserID = createdUserID;
        this.updatedTime = updatedTime;
        this.updatedUserID = updatedUserID;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", createdTime=" + createdTime +
                ", createdUserID='" + createdUserID + '\'' +
                ", updatedTime=" + updatedTime +
                ", updatedUserID='" + updatedUserID + '\'' +
                '}';
    }
}
