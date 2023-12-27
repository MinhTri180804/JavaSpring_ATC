package atc.projecttest.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Column(name = "CREATEDAT")
    LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "MODIFIEDAT")
    LocalDateTime modifiedAt = LocalDateTime.now();

    public Blog(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
