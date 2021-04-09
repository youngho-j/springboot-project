package com.youngho.book.springboot.web;

import com.youngho.book.springboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


//페이지에 관련된 컨트롤러
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    //메인 페이지로 이동
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    //글 등록 페이지로 이동
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

}
