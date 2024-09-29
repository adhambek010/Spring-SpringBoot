insert into user_details(birthdate, id, name)
values( current_date(), 10001,'Adham');

insert into user_details(birthdate, id, name)
values( current_date(), 10002,'Ravi');

insert into user_details(birthdate, id, name)
values( current_date(), 10003,'Ranga');

INSERT INTO POST (POST_ID,USER_ID, CONTENT, TITLE)
VALUES
    (1, 10003, 'In this post, we will dive into the fundamentals of Java programming, covering basic syntax, OOP principles, and common data structures.', 'Understanding Java Basics');

INSERT INTO POST (POST_ID,USER_ID, CONTENT, TITLE)
VALUES
    (2, 10001, 'This post explores how to get started with Spring Boot, setting up your first REST API, and deploying it to a cloud platform.', 'Spring Boot for Beginners');

INSERT INTO POST (POST_ID,USER_ID, CONTENT, TITLE)
VALUES
    (3, 10002, 'Here we explain the core concepts of Hibernate and JPA, focusing on how they simplify database interaction in Java applications.', 'Hibernate and JPA Simplified');

INSERT INTO POST (POST_ID,USER_ID, CONTENT, TITLE)
VALUES
    (4, 10002, 'In this article, we will discuss best practices for designing and building scalable and maintainable REST APIs.', 'Best Practices for REST API Development');

INSERT INTO POST (POST_ID,USER_ID, CONTENT, TITLE)
VALUES
    (5, 10003, 'Java Streams provide a modern approach to handle collections in Java. This post demonstrates how to use Streams for more efficient data processing.', 'Exploring Java Streams');
