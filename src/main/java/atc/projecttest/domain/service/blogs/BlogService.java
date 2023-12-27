package atc.projecttest.domain.service.blogs;

import atc.projecttest.domain.entity.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogService {
    Blog save(Blog blog);
    List<Blog> findAll();
    Optional<Blog> findById(Long id);
    void removeById(Long id);
}
