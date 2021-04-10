package com.youngho.book.springboot.web;

import com.youngho.book.springboot.service.PostsService;
import com.youngho.book.springboot.web.dto.PostsResponseDto;
import com.youngho.book.springboot.web.dto.PostsSaveRequestDto;
import com.youngho.book.springboot.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
*   @RequiredArgsConstructor - final이 선언된 모든 필드를 인자값으로 하는 생성자를 생성
*   롬복의 @RequiredArgsConstructor을 통해 생성된 생성자를 통해 Bean을 주입받음
*
*   그럼 왜 생성자를 직접 사용하지 않는지?
*   만약 생성자를 직접 사용한다면 해당 클래스의 의존성 관계가 변경될 떄마다 생성자 코드를 계속 변경해줘야함
*   근데 @RequiredArgsConstructor를 사용한다면 이러한 번거로움이 해결됨
*
*   @RequiredArgsConstructor를 한번만 쓸 것이냐? 아니면 계속 변경해 줄 것이냐의 차이!
* */
@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;
    
    //등록 기능
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    //수정 기능
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
