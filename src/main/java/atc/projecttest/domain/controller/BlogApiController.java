package atc.projecttest.domain.controller;

import atc.projecttest.domain.controller.blogDTO.BlogRequestDTO;
import atc.projecttest.domain.controller.blogDTO.BlogResponseDTO;
import atc.projecttest.domain.entity.Blog;
import atc.projecttest.domain.service.blogs.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogApiController {

    BlogService blogService;

    public BlogApiController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/blogs")
    public BlogResponseDTO saveBlog(@RequestBody BlogRequestDTO newBlog) {
        Blog blog = blogService.save(new Blog(newBlog.getTitle(), newBlog.getContent()));
        return new BlogResponseDTO(blog);
    }

    @GetMapping("/blogs")
    public List<BlogResponseDTO> findAll() {
        List<Blog> blogsList = blogService.findAll();
        return new BlogResponseDTO().convertList(blogsList);
    }

    @GetMapping("/blogs/{id}")
    public BlogResponseDTO findById(@PathVariable("id") Long id) {
        Blog blog = blogService.findById(id).get();
        BlogResponseDTO blogResponseDTO = new BlogResponseDTO(blog);
        return blogResponseDTO;
    }
}
