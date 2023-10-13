package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  message body의 내용을
        // bytecode로 바로 얻을 수 있음.
        ServletInputStream inputStream = request.getInputStream();
        // StreamUtils는 spring에서 제공하는 유틸리티
        // + bytecode를 문자로 변환할 땐
        //  항상 인코딩 정보 알려줘야 함
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);
//        System.out.println("messageBody = " + inputStream);

        response.getWriter().write("ok");
    }
}
