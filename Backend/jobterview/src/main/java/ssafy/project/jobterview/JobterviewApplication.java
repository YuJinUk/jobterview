package ssafy.project.jobterview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.repository.MemberRepository;

@EnableJpaAuditing
@SpringBootApplication
public class JobterviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobterviewApplication.class, args);


	}
}
