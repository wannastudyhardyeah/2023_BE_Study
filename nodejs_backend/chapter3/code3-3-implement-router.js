/*
* createServer() 리팩터링 하기
* : code3-2에선 req에 대한 res를
*           createServer() 안에서 직접 컨트롤 함
*   ==> 좋지 않음
*       (createServer() 안의 콜백 함수에
*           모든 코드를 다 추가해야 하므로)
*   ====> 별도의 함수 만들어 처리하기!!
* */

const http = require("http");
const url = require("url");

http
    .createServer((req, res) => {
        const path = url.parse(req.url, true).pathname;
        res.setHeader("Content-Type", "text/html");

        if (path === "/user") {
            // user() 함수 실행
            user(req, res);
        } else if (path === "/feed") {
            // feed() 함수 실행
            feed(req, res);
        } else {
            // notFound() 함수 실행
            notFound(req, res);
        }
    })
    .listen("3000",
        () => console.log("라우터를 만들어보자!"));

const user = (req, res) => {
    res.end(`[user] name: andy, age: 30`);
};

const feed = (req, res) => {
    res.end(`<ul>
        <li>picture1</li>
        <li>picture2</li>
        <li>picture3</li>
        </ul>
    `);
};

const notFound = (req, res) => {
    res.statusCode = 404;
    res.end("404 page not found");
};

