package com.youngho.book.springboot.web.dto;

import com.youngho.book.springboot.domain.posts.Posts;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
