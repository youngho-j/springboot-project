package com.youngho.book.springboot.web;

import com.youngho.book.springboot.config.auth.LoginUser;
import com.youngho.book.springboot.config.auth.dto.SessionUser;
import com.youngho.book.springboot.service.PostsService;
import com.youngho.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;


//페이지에 관련된 컨트롤러
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
//  private final HttpSession httpSession;

    //메인 페이지로 이동
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user ) {
        model.addAttribute("posts", postsService.findAllDesc());

        // 개선이 필요한 부분 - 왜? 다른 컨트롤러와 메서드에서 세션값 필요시 그때마다 직접 세션에서 값을 가져와야함
        // config.auth 패키지 LoginUser에 추가
        // 기존 코드  SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null) {
            model.addAttribute("userLoginName", user.getName());
        }
        return "index";
    }

    //글 등록 페이지로 이동
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    //글 수정 페이지로 이동
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        return "posts-update";
    }
}
