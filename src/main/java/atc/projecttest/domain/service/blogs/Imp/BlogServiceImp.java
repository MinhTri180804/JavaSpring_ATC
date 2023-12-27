package atc.projecttest.domain.service.blogs.Imp;

import atc.projecttest.domain.entity.Blog;
import atc.projecttest.domain.service.blogs.BlogService;
import atc.projecttest.repository.BlogRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class BlogServiceImp implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImp(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog save(Blog blog) {
        validatedDuplicate(blog.getTitle());
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return Optional.of(blogRepository.findById(id).get());
    }

    public void validatedDuplicate(String title) {
        blogRepository.findByTitle(title).ifPresent(blogs -> {
            throw new IllegalStateException("Title blog is exits");
        });
    }

    @Override
    public void removeById(Long id) {
        blogRepository.removeById(id);
    }
}


