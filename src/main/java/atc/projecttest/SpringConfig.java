package atc.projecttest;
import atc.projecttest.domain.controller.BlogApiController;
import atc.projecttest.domain.controller.BlogController;
import atc.projecttest.domain.service.blogs.Imp.BlogServiceImp;
import atc.projecttest.repository.BlogRepository;
import atc.projecttest.repository.JdbcTemplateRepository;
import atc.projecttest.repository.JpaBlogRepository;
import atc.projecttest.repository.MemoryBlogRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
    private final EntityManager entityManager;

    @Autowired
    public SpringConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Bean
    public BlogRepository blogRepository() {
//        return new JdbcTemplateRepository(dataSource);
        return new JpaBlogRepository(entityManager);
    }

    @Bean
    public BlogServiceImp blogServiceImp(BlogRepository blogRepository) {
        return new BlogServiceImp(blogRepository());
    }

    @Bean
    public BlogController blogController(BlogServiceImp blogServiceImp) {
        return new BlogController(blogServiceImp(blogRepository()));
    }

    @Bean
    public BlogApiController blogApiController(BlogServiceImp blogServiceImp) {
        return new BlogApiController(blogServiceImp(blogRepository()));
    }


}
