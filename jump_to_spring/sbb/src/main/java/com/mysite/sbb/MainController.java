package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLOutput;

@Controller
public class MainController {

    @GetMapping("/sbb")
    /*ResponseBody 생략 시
    "index" 이름의 템플릿 파일 찾음 */
    @ResponseBody
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String root() {
        // "localhost:8080"으로 접속해도 질문 목록 리다이렉트
        return "redirect:/question/list";
    }
}
