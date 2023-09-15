package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }
    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                         @RequestBody ArticleForm dto) {
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
            log.info("잘못된 요청! id: {}, article: {}", id, article, toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 4. 업데이트 및 정상응답하기(200)
        //  (대상 엔터티 있으면
        //      수정내용으로 업데이트하고
        //  정상응답(200) 보내기)
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        // 1. DB에서 대상 엔터티 있는지 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 대상 엔터티 없어서
        //      요청 자체가 잘못됐을 경우 처리
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 3. 대상 엔터티 있으면 삭제하고
        //      정상 응답(200) 반환
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    // DELETE

}