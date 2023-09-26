package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 이거 달게된 이상,
// 이 클래스의 모든 Mapping은 "/question"으로 시작!!
@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;

    //    @GetMapping("/question/list")
    // 클래스 annot.에서 RequestMapping 썼으므로 이렇게 가능
    @GetMapping("/list")
//    @ResponseBody
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        // Model 객체는 자바 Class와 Template 간의 연결고리 역할
        // 즉, Model 객체에 값 담아두면 템플릿에서도 그 값 사용가능
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    //    @GetMapping(value = "/question/detail/{id}")
    // 클래스 annot.에서 RequestMapping 썼으므로 이렇게 가능
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model,
                         @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate() {
        return "question_form";
    }

    // 메서드 오버라이딩이라 가능함.
    // 화면에 입력한 "제목"과 "내용"을 매개변수로.
    @PostMapping("/create")
    public String questionCreate(@RequestParam String subject,
                                 @RequestParam String content) {
        // 질문 저장
        this.questionService.create(subject, content);
        // 질문 저장 이후 질문목록으로 리다이렉트
        return "redirect:/question/list";
    }
}
