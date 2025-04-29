package learn.springboot.learnjpaandhibernate.course;

import learn.springboot.learnjpaandhibernate.course.jpa.JpaRepository;
import learn.springboot.learnjpaandhibernate.course.springdatajpa.SpringDataJpaRepository;
import learn.springboot.learnjpaandhibernate.jdbc.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

//    @Autowired
//    private JdbcRepository repository;
//    @Autowired
//    private JpaRepository repository;

    @Autowired
    private SpringDataJpaRepository repository;
    @Override
    public void run(String... args) throws Exception {
        repository.save(new Course(2, "Learn AWS Jpa!", "in28minutes"));
        repository.save(new Course(3, "Learn Azure Jpa!", "Tech With Tim"));
        repository.save(new Course(4, "MySql Intermediate Jpa", "techTFQ"));
        repository.save(new Course(5, "Easy Learn DevOps Jpa!", "Simply Learn"));

        System.out.println(repository.findById(5L));
        System.out.println(repository.findById(3L));
        repository.findAll().forEach(System.out::println);
        System.out.println(repository.count());
        repository.findByAuthor("techTFQ").forEach(System.out::println);
        repository.findByName("Learn AWS Jpa!").forEach(System.out::println);
    }
}
