/*
*  라우터 관련 코드 리팩터링
*   => 분기문 대신 Map 자료구조
* */

const http = require("http");
const url = require("url");

http
    .createServer((req, res) => {
        const path = url.parse(req.url, true).pathname;
        res.setHeader("Content-Type", "text/html");

        // urlMap에 path가 있는지 확인
        if (path in urlMap) {
            // urlMap에 path값으로 mapping된 함수 실행
            urlMap[path](req, res);
        } else {
            notFound(req, res);
        }
    })
    .listen("3000", () => console.log("라우터를 만들어보자! - 2"));


const user = (req, res) => {
    // query String 데이터를 userInfo에 할당
    const userInfo
        = url.parse(req.url, true).query;
    // 결괏값으로 이름과 나이 설정
    res.end(`[user] name: ${userInfo.name}, age: ${userInfo.age}`);
};

const feed = (req, res) => {
    res.end(`<ul>
        <li>picutre1</li>
        <li>picutre2</li>
        <li>picutre3</li>
        </ul>
        `);
};

const notFound = (req, res) => {
    res.statusCode = 404;
    res.end("404 page not found");
};

// 라우터 규칙 mapping key로
// path가 들어가고, 값에 함수를 할당
const urlMap = {
    "/": (req, res) => res.end("HOME"),
    "/user": user,
    "/feed": feed,
};

