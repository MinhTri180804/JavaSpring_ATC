package atc.projecttest.repository;

import atc.projecttest.domain.entity.Blog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateRepository implements BlogRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Blog save(Blog blog) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("blog").usingGeneratedKeyColumns("id");

        HashMap<String, Object> parameter = new HashMap<>();
        parameter.put("title", blog.getTitle());
        parameter.put("content", blog.getContent());
        parameter.put("createdAt", blog.getCreatedAt());
        parameter.put("modifiedAt", blog.getModifiedAt());

        Number idBlog = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameter));
        blog.setId(idBlog.longValue());
        return blog;
    }

    @Override
    public List<Blog> findAll() {
        String sql = "SELECT * FROM blog";
        return jdbcTemplate.query(sql, blogsRowMapper());

    }

    @Override
    public Optional<Blog> findById(Long id) {
        String sql = "SELECT * FROM blog WHERE id = ?";
        List<Blog> result = jdbcTemplate.query(sql, blogsRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Blog> findByTitle(String title) {
        String sql = "SELECT * FROM blog WHERE title = ?";
        List<Blog> result = jdbcTemplate.query(sql, blogsRowMapper(), title);
        return result.stream().findAny();
    }

    @Override
    public void removeById(Long id) {
        String sql = "DELETE FROM blog WHERE id = " + id;
        jdbcTemplate.execute(sql);
    }

    private RowMapper<Blog> blogsRowMapper() {
        return ((rs, rowNum) -> {
            Blog blog = new Blog();
            blog.setId(rs.getLong("id"));
                blog.setTitle(rs.getString("title"));
            blog.setContent(rs.getString("content"));
            blog.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            blog.setModifiedAt(rs.getTimestamp("modifiedAt").toLocalDateTime());
            return blog;
        }
        );
    }
}
