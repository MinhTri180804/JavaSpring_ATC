package atc.projecttest.repository;

import atc.projecttest.domain.entity.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogRepository {
    Blog save(Blog blog);
    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    Optional<Blog> findByTitle(String name);

    void removeById(Long id);
}
