package com.spring.codeblog.repositories;

import com.spring.codeblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeblogRepository extends JpaRepository<Post, Long> {
}
