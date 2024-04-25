package kr.ac.kopo.guestbook2024;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //날짜 자동 처리
public class Guestbook2024Application {
    public static void main(String[] args) {
        SpringApplication.run(Guestbook2024Application.class, args);
    }

}
