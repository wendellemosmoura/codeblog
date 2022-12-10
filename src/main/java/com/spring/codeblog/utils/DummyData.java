package com.spring.codeblog.utils;

import com.spring.codeblog.models.Post;
import com.spring.codeblog.repositories.CodeblogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyData {

    @Autowired
    CodeblogRepository codeblogRepository;

    // A anotation "@PostConstruct" deve ser comentada após o primeiro uso, pois a cada inicialização do projeto ele salvará novamente os posts.
    //@PostConstruct
    public void savePosts() {

        List<Post> postList = new ArrayList<>();

        Post post1 = new Post();
        post1.setAutor("Wendel Lemos");
        post1.setTitulo("Docker");
        post1.setData(LocalDate.now());
        post1.setTexto("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        Post post2 = new Post();
        post2.setAutor("Wendel Lemos");
        post2.setTitulo("REST API");
        post2.setData(LocalDate.now());
        post2.setTexto("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");

        postList.add(post1);
        postList.add(post2);

        for (Post post: postList) {
            Post postSaved = codeblogRepository.save(post);
            System.out.println(postSaved.getId());
        }
    }
}
