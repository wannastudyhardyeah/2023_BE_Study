package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    // 입력 폼에서
    // '제목', '내용' 전송
    private String title;
    private String content;

    // 생성자

    public Article toEntity() {
        return new Article(id, title, content);
    }
}