package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        /*// 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔터티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }*/
        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 1. DB에서 부모 게시글 조회해 가져오고
        //  없을 경우 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!" +
                        "대상 게시글이 없습니다."));  // 에러 출력
        // 2. 부모 게시글의
        //  새 댓글 엔터티 생성
        Comment comment = Comment.createComment(dto, article);
        // 3. 생성된 엔터티를
        //  DB에 저장
        Comment created = commentRepository.save(comment);
        // 4. DB에 저장한 엔터티를
        //  DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 1. DB에서 해당 댓글 조회해 가져오고
        //  없을 경우 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" +
                        "대상 댓글이 없습니다."));  // 에러 출력
        // 2. 가져온 댓글 내용 수정
        target.patch(dto);
        // 3. 수정한 댓글을
        //  DB에 갱신하기
        // (수정 데이터로 덮어쓰기)
        Comment updated = commentRepository.save(target);
        // 4. DB에 저장한 엔터티를
        //  DTO로 변환해 컨트롤러로 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 1. DB에서 해당 댓글 조회해 가져오고
        //  없을 경우 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! " +
                        "대상이 없습니다."));
        // 2. 가져온 댓글을
        //  DB에서 삭제
        commentRepository.delete(target);
        // 3. 삭제한 댓글을
        //  DTO로 변환해 반환
        return CommentDto.createCommentDto(target);
    }
}
