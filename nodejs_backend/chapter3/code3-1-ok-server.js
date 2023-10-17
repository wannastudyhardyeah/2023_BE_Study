const http = require("http");
const server
    = http.createServer(
        (req, res)=>
        {
            // 응답의 헤더 설정
            // : 텍스트를 html로 해석하겠다는 의미
            res.setHeader("Content-Type", "text/html");

            // "OK"를 응답하고 종료
            res.end("OK");
    });

// 접속 대기
server.listen("3000", () => console.log("OK 서버 시작!"));