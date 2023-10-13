package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            printStartLine(request);
            printHeaders(request);
//            printHeaderUtils(request);
//            printEtc(request);
            response.getWriter().write("ok");
    }

    //start line 정보
    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        //GET
        System.out.println("request.getMethod() = " + request.getMethod());
        //HTTP/1.1
        System.out.println("request.getProtocol() = " + request.getProtocol());
        // http://localhost:8080/request-header
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        // /request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());
        //username=hi
        System.out.println("request.getRequestURI() = " + request.getRequestURI());
        System.out.println("request.getQueryString() = " +
                request.getQueryString());
        //https 사용 유무
        System.out.println("request.isSecure() = " + request.isSecure());
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    //Header 모든 정보
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + ": " + headerName);
//        }

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName +
                        ": " + request.getHeader(headerName)));
        System.out.println("--- Headers - end ---");
        System.out.println();
    }
}
