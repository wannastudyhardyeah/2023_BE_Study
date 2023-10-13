<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // request, response는 import 없이도 사용 가능!
  MemberRepository memberRepository = MemberRepository.getInstance();

  System.out.println("save.jsp");
  // form으로부터 온 요청에서 패러미터 꺼내기
  // query string이든 POST의 form 방식이든 getParameter로는 다 가능!
  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  // 비즈니스 로직
  Member member = new Member(username, age);
  memberRepository.save(member);
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
성공
<ul>
  <li>id=<%=member.getId()%></li>
  <li>username=<%=member.getUsername()%></li>
  <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
