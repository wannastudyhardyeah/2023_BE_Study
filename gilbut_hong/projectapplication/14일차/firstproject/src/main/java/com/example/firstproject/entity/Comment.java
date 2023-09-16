package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    // 대표키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 해당 댓글의 부모 게시글
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    // 댓글 작성자
    @Column
    private String nickname;

    // 댓글 본문
    @Column
    private String body;
}
