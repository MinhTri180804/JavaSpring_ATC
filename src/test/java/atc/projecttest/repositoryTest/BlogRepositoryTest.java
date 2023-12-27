package atc.projecttest.repositoryTest;

import atc.projecttest.domain.entity.Blog;
import atc.projecttest.repository.MemoryBlogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BlogRepositoryTest {
    MemoryBlogRepository memoryBlogRepository = new MemoryBlogRepository();

    @AfterEach
    public void clearStore() {
        memoryBlogRepository.clearStore();
    }

    @Test
    public void save() {

        int countBlogAdd = 3;
//        given
        Blog blog1 = new Blog();
        blog1.setTitle("Bai viet 1");
        blog1.setContent("Noi dung bai viet 1");
        memoryBlogRepository.save(blog1);

        Blog blog2 = new Blog();
        blog2.setTitle("Bai viet 2");
        blog2.setContent("Noi dung bai viet 2");
        memoryBlogRepository.save(blog2);


        Blog blog3 = new Blog();
        blog3.setTitle("Bai viet 3");
        blog3.setContent("Noi dung bai viet 3");
        memoryBlogRepository.save(blog3);


//        then
        List<Blog> blogsList = memoryBlogRepository.findAll();
        Assertions.assertThat(countBlogAdd).isEqualTo(blogsList.size());
    }

    @Test
    public void findById() {
        Long idFind = 3L;

        Blog blog1 = new Blog();
        blog1.setTitle("Bai viet 1");
        blog1.setContent("Noi dung bai viet 1");
        memoryBlogRepository.save(blog1);

        Blog blog2 = new Blog();
        blog2.setTitle("Bai viet 2");
        blog2.setContent("Noi dung bai viet 2");
        memoryBlogRepository.save(blog2);


        Blog blog3 = new Blog();
        blog3.setTitle("Bai viet 3");
        blog3.setContent("Noi dung bai viet 3");
        memoryBlogRepository.save(blog3);

//       where

        Blog result = memoryBlogRepository.findById(idFind).get();

        Assertions.assertThat(idFind).isEqualTo(result.getId());
    }

    @Test
    public void findByTitle() {
        Blog blog1 = new Blog();
        blog1.setTitle("Bai viet 1");
        blog1.setContent("Noi dung bai viet 1");
        memoryBlogRepository.save(blog1);

        Blog blog2 = new Blog();
        blog2.setTitle("Bai viet 2");
        blog2.setContent("Noi dung bai viet 2");
        memoryBlogRepository.save(blog2);


        Blog blog3 = new Blog();
        blog3.setTitle("Bai viet 3");
        blog3.setContent("Noi dung bai viet 3");
        memoryBlogRepository.save(blog3);

        String titleTest = "Bai viet 2";
        Blog result = memoryBlogRepository.findByTitle(titleTest).get();

        Assertions.assertThat(titleTest).isEqualTo(result.getTitle());
    }
}
