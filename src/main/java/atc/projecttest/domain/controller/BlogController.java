package atc.projecttest.domain.controller;

import atc.projecttest.domain.controller.blogDTO.BlogRequestDTO;
import atc.projecttest.domain.controller.blogDTO.BlogResponseDTO;
import atc.projecttest.domain.entity.Blog;
import atc.projecttest.domain.service.blogs.Imp.BlogServiceImp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BlogController {

    BlogServiceImp blogServiceImp;

    public BlogController(BlogServiceImp blogServiceImp) {
        this.blogServiceImp = blogServiceImp;
    }

    @GetMapping("/blog/create")
    public String createBlog() {
        return "blogs/createBlog";
    }

    @GetMapping("/blog/blogList")
    public String listBlog(Model model) {
        List<Blog> blogsList = blogServiceImp.findAll();
        model.addAttribute("blogList", new BlogResponseDTO().convertList(blogsList));
        return "blogs/blogList";
    }

    @PostMapping("/blog/create/new")
    public String saveBlog(BlogRequestDTO blogRequestDTO) {
        blogServiceImp.save( new BlogRequestDTO().getBlog(blogRequestDTO));
        return "redirect:/blog/blogList";
    }

    @GetMapping("blog/remove/{id}")
    public String removeBlog(@PathVariable Long id) {
        blogServiceImp.removeById(id);
        return "redirect:/blog/blogList";
    }
}
