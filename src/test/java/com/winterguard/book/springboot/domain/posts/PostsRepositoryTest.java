package com.winterguard.book.springboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // Given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        String author = "git@winterguard";

        postsRepository.save(
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author(author)
                        .build()
        );

        // When
        List<Posts> postsList = postsRepository.findAll();

        // Then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        // Given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);

        String title = "title";
        String content = "content";
        String author = "author";

        postsRepository.save(
                Posts.builder()
                    .title(title)
                    .content(content)
                    .author(author)
                    .build()
        );

        // When
        List<Posts> postsList = postsRepository.findAll();

        // Then
        Posts posts = postsList.get(0);

        System.out.printf("%n[Custom User Console]%n");
        System.out.printf("Post.createDate: %s%n", posts.getCreatedDate());
        System.out.printf("Post.modifiedDate: %s%n%n", posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
