package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;


    @PostMapping("/create/{id}")
    public String createAnswer(Model model,
                               @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm,
                               BindingResult bindingResult) {
    /* *템플릿에 입력한 내용 얻기 위해 추가*
        @RequestParam String content*/
        Question question = this.questionService.getQuestion(id);
        // 검증에 실패할 경우
        if (bindingResult.hasErrors()) {
            // 다시 답변 등록 가능하도록
            model.addAttribute("question", question);
            // question 템플릿 다시 렌더링
            return "question_detail";
        }
        // 답변 저장 위해선 답변 서비스 필요
        this.answerService.create(question, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }
}
