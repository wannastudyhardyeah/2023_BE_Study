package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
