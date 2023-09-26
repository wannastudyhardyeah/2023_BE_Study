package com.mysite.sbb.question;

import org.springframework.data.jpa.repository.JpaRepository;

// 엔터티의 타입: Question
// PK의 속성 타입: Integer
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
