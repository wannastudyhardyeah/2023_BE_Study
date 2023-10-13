package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // 여기선 model도 만들 필요가 없다!
        // FrontController에서 만들어서 넘겨줄 거라서
        return "new-form";
    }
}
