package com.example.firstproject.repository;

/* CrudRepository
 이를 상속해 엔터티를 관리(생성, 조회, 수정, 삭제) 가능*/
import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

/*
* Article   : 클래스 타입
* Long      : 대푯값 타입
* */
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}