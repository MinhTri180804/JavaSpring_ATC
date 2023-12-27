package atc.projecttest.domain.controller.blogDTO;

import atc.projecttest.domain.entity.Blog;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class BlogResponseDTO {
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private String createdAt;

    @Getter
    @Setter
    private String modifiedAt;

    final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");


    public BlogResponseDTO() {}

    public BlogResponseDTO(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.createdAt = blog.getCreatedAt().format(format);
        this.modifiedAt = blog.getModifiedAt().format(format);
    }

    public List<BlogResponseDTO> convertList(List<Blog> blogsList) {
        List<BlogResponseDTO> listResult = new ArrayList<>();
        for(Blog blog : blogsList) {
            listResult.add(new BlogResponseDTO(blog));
        }
        return listResult;
    }

}
