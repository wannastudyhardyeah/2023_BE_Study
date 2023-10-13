package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("resp = " + response);

        String username = request.getParameter("username");
        System.out.println("username = " + username);

    // 아래 이건 header 정보에 들어감
        // 응답 메시지 => response에.
        response.setContentType("text/plain");
        // 인코딩 알려줌
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
