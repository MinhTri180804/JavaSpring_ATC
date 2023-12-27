package atc.projecttest.repository;

import atc.projecttest.domain.entity.Blog;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaBlogRepository implements BlogRepository {

    private final EntityManager entityManager;

    public JpaBlogRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Blog save(Blog blog) {
        entityManager.persist(blog);
        return blog;
    }

    @Override
    public List<Blog> findAll() {
        return null;
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Blog.class, id));
    }

    @Override
    public Optional<Blog> findByTitle(String name) {
        return Optional.empty();
    }

    @Override
    public void removeById(Long id) {

    }
}
