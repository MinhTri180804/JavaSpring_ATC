package atc.projecttest.domain.controller.blogDTO;

import atc.projecttest.domain.entity.Blog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlogRequestDTO {
    private String title;
    private String content;

    public BlogRequestDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public Blog getBlog(BlogRequestDTO blogRequestDTO) {
        return new Blog(blogRequestDTO.getTitle(), blogRequestDTO.getContent());
    }
}
