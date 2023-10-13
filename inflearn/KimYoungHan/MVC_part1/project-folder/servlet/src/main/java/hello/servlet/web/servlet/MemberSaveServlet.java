package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");
        // form으로부터 온 요청에서 패러미터 꺼내기
        // query string이든 POST의 form 방식이든 getParameter로는 다 가능!
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // 비즈니스 로직
        Member member = new Member(username, age);
        memberRepository.save(member);

        // 결과를 HTML로 응답함
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();

        // 동적 HTML인 경우
        /*
        * 정적인 경우는 "~.html" 파일
        *
        */
        w.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "   <meta charset=\"UTF-8\">\n" +
                "</head>" +
                "<body>" +
                "성공\n" +
                "<ul>\n" +
                "   <li>id=" + member.getId() + "</li>\n" +
                "   <li>username=" + member.getUsername() + "</li>\n" +
                "   <li>age=" + member.getAge() + "</li>\n" +
                "</ul>" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
