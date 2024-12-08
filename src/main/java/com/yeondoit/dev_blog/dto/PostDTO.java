package com.yeondoit.dev_blog.dto;

import com.yeondoit.dev_blog.domain.Category;
import com.yeondoit.dev_blog.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;

    private String title;

    private String content;

    private String author;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private Integer views;

    private Category category;

    private List<String> tags;

    private PostStatus status;

}
