package com.mysite.sbb.question;


import com.mysite.sbb.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
/*
* 일반적으론 엔터티엔 Setter 구현 안 하고 사용함.
* why? 엔터티는 DB와 바로 연결되어 있으므로.
*
* So, 엔터티에 값 생성, 저장하기 위해선
*   생성: 롬복의 @Builder 애너테이션
*   변경: 그에 해당되는 메서드를 엔터티에 추가하기
* */
@Setter
@Entity
public class Question {
    // @Id 설정하면 동일한 값 저장 불가능
    @Id // (기본키)
    // 자동 증가
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    // - Question 객체에서 답변 참조할 시에
    // question.getAnswerList() 호출하면 됨
    // - cascade: 질문 삭제 시 답변들 다 삭제
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
