package com.youngho.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//페이지에 관련된 컨트롤러
@Controller
public class IndexController {
    //메인 페이지로 이동
    @GetMapping("/")
    public String index(){
        return "index";
    }
    //글 등록 페이지로 이동
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
