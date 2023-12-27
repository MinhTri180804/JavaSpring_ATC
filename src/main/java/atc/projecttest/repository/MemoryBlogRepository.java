package atc.projecttest.repository;

import atc.projecttest.domain.entity.Blog;

import java.time.LocalDateTime;
import java.util.*;

public class MemoryBlogRepository implements BlogRepository {
    private static Map<Long, Blog> store = new HashMap<>();
    private static Long countId = 0L;

    @Override
    public Blog save(Blog blog) {
        blog.setId(++countId);
        blog.setCreatedAt(LocalDateTime.now());
        blog.setModifiedAt(LocalDateTime.now());
        store.put(blog.getId(), blog);
        return blog;
    }

    @Override
    public List<Blog> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return Optional.of(store.get(id));
    }

    @Override
    public Optional<Blog> findByTitle(String name) {
        return store.values().stream().filter(blogs -> blogs.getTitle().equals(name)).findAny();
    }

    @Override
    public void removeById(Long id) {

    }

    public void clearStore() {
        store.clear();
    }
}
