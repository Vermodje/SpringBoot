package base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class MainApp {
/*    INSERT INTO db_example.roles (id, role) values(1, 'ROLE_ADMIN');
    INSERT INTO db_example.roles (id, role) values(2, 'ROLE_USER');
    INSERT INTO db_example.news (id, headline, description) values(1, 'EURONEWS', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed viverra.');
    INSERT INTO db_example.news (id, headline, description) values(2, 'SPORTNEWS', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed viverra.');
    INSERT INTO db_example.news (id, headline, description) values(3, 'WEATHER NEWS', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed viverra.');


    INSERT INTO db_example.comments (id, text, user_id) values(1, 'Hello world', '1');
    INSERT INTO db_example.comments (id, text, user_id) values(2, 'Hello java', '2');
    INSERT INTO db_example.comments (id, text, user_id) values(3, 'It is good', '1');
    INSERT INTO db_example.comments (id, text, user_id) values(4, 'Not bad', '2');
    INSERT INTO db_example.news_comments (news_id, comments_id) values(1, 1);
    INSERT INTO db_example.news_comments (news_id, comments_id) values(1, 2);
    INSERT INTO db_example.news_comments (news_id, comments_id) values(2, 3);
    INSERT INTO db_example.news_comments (news_id, comments_id) values(3, 4);
    INSERT INTO db_example.users_comments (user_id, comments_id) values(1, 1);
    INSERT INTO db_example.users_comments (user_id, comments_id) values(1, 3);*/
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}
