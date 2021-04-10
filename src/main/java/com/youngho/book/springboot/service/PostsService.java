package com.youngho.book.springboot.service;

import com.youngho.book.springboot.domain.posts.Posts;
import com.youngho.book.springboot.domain.posts.PostsRepository;
import com.youngho.book.springboot.web.dto.PostsListResponseDto;
import com.youngho.book.springboot.web.dto.PostsResponseDto;
import com.youngho.book.springboot.web.dto.PostsSaveRequestDto;

import com.youngho.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    public final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        /*
        *   보통 update 기능을 만들면 데이터베이스에 쿼리를 날려야하는데 여기에서는 날리는 부분이 없음..
        *
        *   어떻게 이러한 상황이 가능한가?
        *   JPA의 영속성 컨텍스트 덕분
        *
        *   그럼 영속성 컨텍스트는 무엇인가?
        *   엔티티를 영구 저장하는 환경이며, 일종의 논리적 개념으로 보면됨
        *
        *   JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈림
        *
        *   영속성 컨텍스트에 포함되어있다는 것은
        *   JPA의 엔티티 매니저가 활성화된 상태(Spring Data Jpa 사용시는 기본적으로 적용되어있음)로
        *   트랜잭션 안에서 데이터베이스에서 데이터를 가져오는 경우 데이터는 영속성 컨텍스트가 유지된 상태임
        *
        *   이 상태에서 해당 데이터의 값을 변경시 트랜잭션이 끝나는 시점에 해당 테이블의 변경 분을 반영
        *
        *   즉, Entity 객체의 값만 변경하면 별도로 update 쿼리를 날릴 필요가 없음
        *
        *   이러한 개념을 더티체킹(상태 변경 검사)이라고 함
        * */
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
    /*
    *   @Transactional(readOnly = true)
    *   - 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선
    *     등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는 것 추천
    * */
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        /*  .map(PostsListRespomseDto::new) => .map(posts -> new PostsListResponseDto(posts))
        *   postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsResponseDto로 변환하여 List로 반환하는 메소드
        * */
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        //존재하는 Posts 인지 확인
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        // 맞는 경우 삭제
        postsRepository.delete(posts);
    }
}
