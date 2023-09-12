package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;

public class ArticleForm {
    // 입력 폼에서
    // '제목', '내용' 전송
    private String title;
    private String content;

    // 생성자
    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity() {
        return new Article(null, title, content);
    }
}