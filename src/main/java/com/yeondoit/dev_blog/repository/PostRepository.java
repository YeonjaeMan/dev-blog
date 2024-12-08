package com.yeondoit.dev_blog.repository;

import com.yeondoit.dev_blog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
