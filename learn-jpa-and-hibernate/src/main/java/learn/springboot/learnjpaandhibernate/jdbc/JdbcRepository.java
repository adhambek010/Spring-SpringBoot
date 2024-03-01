package learn.springboot.learnjpaandhibernate.jdbc;

import learn.springboot.learnjpaandhibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepository {

    @Autowired
    private JdbcTemplate template;
    private static final String INSERT_QUERY =
            """
                INSERT INTO COURSE(id, name, author)
                values(?, ?, ?);
            """;
    private static final String DELETE_QUERY =
        """
            DELETE FROM COURSE WHERE id = ?;  
        """;
    private static final String SELECT_QUERY =
            """
                SELECT * FROM COURSE
                WHERE id = ?;  
            """;
    public void insert(Course course){
        template.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }
    public void deleteById(long id){
        template.update(DELETE_QUERY, id);
    }
    public Course findById(long id){
        return template.queryForObject(SELECT_QUERY, new BeanPropertyRowMapper<>(Course.class), id);
        // ResultSet -> Bean => Row Mapper
        // id
    }
}
