/*
*   [라우터 만들기]
*   라우팅: URL 경로에 따라서 다른 response를 줌
* */

/*
*  "~/user"와 "~/feed"라는
*   두 URL 있다고 가정.
*   => 두 req에 대하여 다른 res를 주는 코드
* */

const http = require("http");
// url 모듈 로딩
const url = require("url");

http
    .createServer((req, res) => {
        // path 이름 할당
        const path = url.parse(req.url, true).pathname
        res.setHeader("Content-Type", "text/html");

        // "/user" 결괏값 설정
        if (path === "/user") {
            res.end("[user] name: andy, age: 30");
            // "/feed" 결괏값 설정
        } else if (path === "/feed") {
            res.end(`<ul>
            <li>picture1</li>
            <li>picture2</li>
            <li>picture3</li>
            </ul>
            `);
        } else {
            res.statusCode = 404;
            // 결괏값으로 에러 메시지 설정
            res.end("404 page not found");
        }
    })
    .listen("3000", () => console.log("라우터를 만들어보자!"));