package com.maxhax.core;

import com.maxhax.course.Course;
import com.maxhax.course.CourseRepository;
import com.maxhax.review.Review;
import com.maxhax.user.User;
import com.maxhax.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CourseRepository courses;
    private final UserRepository users;
    @Autowired
    public DatabaseLoader(CourseRepository courses, UserRepository users) {
        this.users = users;
        this.courses = courses;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Course course = new Course("Java Basics", "https://maxhax.com/libary/java-basics");
        course.addReview(new Review(3,"You are the best"));
        courses.save(course);

        String[] templates = {
                "Up and Running with %s",
                "%s Basics",
                "%s for Beginners",
                "%s for masters",
                "under the hood: %s"
        };

        String[] buzzwords = {
                "Spring REST",
                "Java 10",
                "Scala",
                "Groovy",
                "Hibernate",
                "Spring Hateos"
        };

        List<User> students = Arrays.asList(
                new User("jacobproffer", "Jacob",  "Proffer", "password", new String[] {"ROLE_USER"}),
                new User("mlnorman", "Mike",  "Norman", "password", new String[] {"ROLE_USER"}),
                new User("k_freemansmith", "Karen",  "Freeman-Smith", "password", new String[] {"ROLE_USER"}),
                new User("seth_lk", "Seth",  "Kroger", "password", new String[] {"ROLE_USER"}),
                new User("mrstreetgrid", "Java",  "Vince", "password", new String[] {"ROLE_USER"}),
                new User("anthonymikhail", "Tony",  "Mikhail", "password", new String[] {"ROLE_USER"}),
                new User("boog690", "AJ",  "Teacher", "password", new String[] {"ROLE_USER"}),
                new User("faelor", "Erik",  "Faelor Shafer", "password", new String[] {"ROLE_USER"}),
                new User("christophernowack", "Christopher",  "Nowack", "password", new String[] {"ROLE_USER"}),
                new User("calebkleveter", "Caleb",  "Kleveter", "password", new String[] {"ROLE_USER"}),
                new User("richdonellan", "Rich",  "Donnellan", "password", new String[] {"ROLE_USER"}),
                new User("albertqerimi", "Albert",  "Qerimi", "password", new String[] {"ROLE_USER"})

        );

        users.saveAll(students);

        users.save(new User("maxelson11","michael","axelson","12345", new String[] {"ROLE_USER", "ROLE_ADMIN"}));


        List<Course> bunchOfCourses = new ArrayList<>();
        IntStream.range(0,100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Course c = new Course(title, "http://www.example.com");
                    Review review = new Review((i %5)+1, String.format("More %s please!", buzzword));
                    review.setReviewer(students.get(i % students.size()));
                    c.addReview(review);
                    bunchOfCourses.add(c);
                });
        courses.saveAll(bunchOfCourses);



    }
}
