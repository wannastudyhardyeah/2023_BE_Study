const express = require("express");
const {application} = require("express");
const app = express();
// 게시글로 사용할 posts에 빈 리스트 할당
let posts = [];

// req.body 사용하려면 JSON 미들웨어 사용해야 함
// 사용하지 않을 시 undefined로 반환
// ## JSON 미들웨어 활성화
app.use(express.json());

// POST 요청 시 
// Content-Type이 application/x~~urlencoded인 경우
// 파싱을 함
/** JSON 미들웨어와 함께 사용 **/
app.use(express.urlencoded({extended: true}));

// "/"로 요청이 오면 실행
app.get("/",
    (req,
     res) => {
    // 게시글 리스트를 JSON 형식으로 보여줌
    res.json(posts);
});

// "/posts"로 요청이 오면 실행
app.post("/posts",
    (req,
     res) => {
    // HTTP 요청의 body 데이터를 변수에 할당
    const {title, name, text} = req.body;

        // 게시글 리스트에
        // 새로운 게시글 정보 추가
        posts.push({
            id: posts.length + 1, title, name, text,
            createdDt: Date()
        });
        res.json({title, name, text});
});

app.delete("/posts/:id",
    (req,
                          res) => {
        // app.delete에 설정한 path 정보에서
        // id값을 가져옴
        const id = req.params.id;
        // 글 삭제 로직
        const filteredPosts = posts.filter((post) => post.id !== +id);
        // 삭제 확인
        const isLengthChanged =
                        posts.length !== filteredPosts.length;
        // posts의 데이터 개수가 변경되었으면 
        // ==> 삭제 성공
        posts = filteredPosts;
        if (isLengthChanged) {
            res.json("OK");
            return;
        }
        // 변경되지 않음
        res.json("NOT CHANGED");
    });

app.listen(3000, () => {
    console.log("welcome posts START!");
});