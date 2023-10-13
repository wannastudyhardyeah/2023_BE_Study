package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. [status-line] 응답 코드 설정
        // => 상수로 정의된 걸 이용하기
        response.setStatus(HttpServletResponse.SC_OK);
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        // 2. [response-headers]
        response.setHeader("Content-Type", "text/plain");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        // [Header 편의 메서드]
//        content(response);
//        cookie(response);
//        redirect(response);

        PrintWriter writer = response.getWriter();
        writer.println("ok");
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
//      response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); <- 생략 시 자동생성
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        // ㄴ 아래의 코드들은 위의 코드와 똑같은 내용임.
        Cookie cookie = new Cookie("myCookie", "good");

        cookie.setMaxAge(600); // 600초
        response.addCookie(cookie);

    /* 참고할 클래스
    *   public Cookie(String name, String value) {
            validation.validate(name);
            this.name = name;
            this.value = value;
        }
    *
    */
    }

    private void redirect(HttpServletResponse response) throws IOException {
        // Status Code: 302
        // Location: /basic/hello-form.html

//        response.setStatus(HttpServletResponse.SC_FOUND); //<-- 302
//        response.setHeader("Location", "/basic/hello-form.html");
        /* 위 코드 둘과 아래 코드 하나는 서로 동등하다.*/
        response.sendRedirect("/basic/hello-form.html");
    }
}
