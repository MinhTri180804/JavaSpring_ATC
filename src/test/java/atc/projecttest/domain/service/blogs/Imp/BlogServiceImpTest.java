package atc.projecttest.domain.service.blogs.Imp;

import atc.projecttest.domain.entity.Blog;
import atc.projecttest.repository.BlogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
class BlogServiceImpTest {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    BlogServiceImp blogServiceImp;

    @Test
    void save() {
        Blog blog1 = new Blog();
        blog1.setTitle("Bai viet 1");
        blog1.setContent("Noi dung bai viet 1");
        Blog savedBlog = blogServiceImp.save(blog1);

        System.out.println(savedBlog.getId());

        Blog result = blogRepository.findById(savedBlog.getId()).get();

        Assertions.assertThat(result.getId()).isEqualTo(savedBlog.getId());
    }

    @Test
    void saveDuplicate() {
        Blog blog1 = new Blog();
        blog1.setTitle("Bai viet 1");
        blog1.setContent("Noi dung bai viet 1");

        Blog blog2 = new Blog();
        blog2.setTitle("Bai viet 1");
        blog2.setContent("Noi dung bai viet 1");

        blogServiceImp.save(blog1);

        org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> blogServiceImp.save(blog2));
    }

    @Test
    void findAll() {
        List<Blog> currentSize = blogServiceImp.findAll();

        Blog blog1 = new Blog();
        blog1.setTitle("Bai viet 1");
        blog1.setContent("Noi dung bai viet 1");

        Blog blog2 = new Blog();
        blog2.setTitle("Bai viet 2");
        blog2.setContent("Noi dung bai viet 1");

        Blog blog3 = new Blog();
        blog3.setTitle("Bai viet 3");
        blog3.setContent("Noi dung bai viet 1");

//        where
        blogServiceImp.save(blog1);
        blogServiceImp.save(blog2);
        blogServiceImp.save(blog3);

        List<Blog> result = blogServiceImp.findAll();

//        then
        Assertions.assertThat(3 + currentSize.size()).isEqualTo(result.size());
    }

    @Test
    void findById() {
        Blog blog1 = new Blog();
        blog1.setTitle("Bai viet 1");
        blog1.setContent("Noi dung bai viet 1");
        Blog savedBlog = blogServiceImp.save(blog1);

        Blog result = blogServiceImp.findById(savedBlog.getId()).get();

        Assertions.assertThat(savedBlog.getId()).isEqualTo(result.getId());
    }
}