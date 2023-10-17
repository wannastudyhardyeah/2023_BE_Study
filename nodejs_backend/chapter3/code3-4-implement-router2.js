/*
*  user() 함수 수정하여
* 매개변수에 따라 동적으로 req 변경되도록 하기!
*
* */

const http = require("http");
const url = require("url");

http
    .createServer((req, res) => {
        const path = url.parse(req.url, true).pathname;
        res.setHeader("Content-Type", "text/html");

        if (path === "/user") {
            user(req, res);
        } else if (path === "/feed") {
            feed(req, res);
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

