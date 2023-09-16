package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }


    public Article update(Long id, ArticleForm dto) {
        // 1. DTO -> 엔터티 변환
        //  (수정용 엔터티 생성)
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // 2. 타겟 조회하기
        // (DB에 대상 엔터티 있는지 조회)
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        // (대상 엔터티 없거나
        //  수정하려는 id 잘못됐을 경우 처리)
        if (target == null || id != article.getId()) {
            // 잘못된 요청 응답(400)
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }

        // 4. 업데이트 및 정상응답하기(200)
        //  (대상 엔터티 있으면
        //      수정내용으로 업데이트하고
        //  정상응답(200) 보내기)
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;

    }

    public Article delete(Long id) {
        // 1. DB에서 대상 엔터티 있는지 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 대상 엔터티 없어서
        //      요청 자체가 잘못됐을 경우 처리
        if (target == null) {
            return null;
        }
        // 3. 대상 엔터티 있으면 삭제하고
        //      정상 응답(200) 반환
        articleRepository.delete(target);
        // DB에서 삭제한 대상을 컨트롤러에 반환
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("결제 실패!"));
        return articleList;
    }
}
