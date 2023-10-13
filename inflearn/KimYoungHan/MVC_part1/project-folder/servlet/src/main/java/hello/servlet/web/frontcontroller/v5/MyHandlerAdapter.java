package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {
    // handler는 컨트롤러를 말함
    // 어댑터가 해당 컨트롤러 처리 가능한지 판단함
    boolean supports(Object handler);

    // 이 어탭터를 통하여 실제 컨트롤러가 호출됨
    ModelView handle(HttpServletRequest request, HttpServletResponse response,
                     Object handler) throws ServletException, IOException;
}
