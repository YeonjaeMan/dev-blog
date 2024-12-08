package com.yeondoit.dev_blog.domain;

import com.yeondoit.dev_blog.dto.PostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "tb_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "views")
    private Integer views;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

    @ElementCollection
    @CollectionTable(name = "tb_post_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "tag")
    private List<String> tags;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private PostStatus status;

    // DTO로 변환하는 메서드
    public PostDTO toDTO() {
        return new PostDTO(
                this.id,
                this.title,
                this.content,
                this.author,
                this.createdDate,
                this.modifiedDate,
                this.views,
                this.category,
                this.tags,
                this.status
        );
    }

    // DTO에서 엔티티로 변환하는 메서드
    public static Post fromDTO(PostDTO postDTO) {
        return new Post(
                postDTO.getId(),
                postDTO.getTitle(),
                postDTO.getContent(),
                postDTO.getAuthor(),
                postDTO.getCreatedDate(),
                postDTO.getModifiedDate(),
                postDTO.getViews(),
                postDTO.getCategory(),
                postDTO.getTags(),
                postDTO.getStatus()
        );
    }
}
