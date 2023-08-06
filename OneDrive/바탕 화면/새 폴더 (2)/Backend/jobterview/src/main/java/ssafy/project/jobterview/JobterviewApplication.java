package ssafy.project.jobterview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JobterviewApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobterviewApplication.class, args);
    }
}
