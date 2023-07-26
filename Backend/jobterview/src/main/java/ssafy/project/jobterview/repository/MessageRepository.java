package ssafy.project.jobterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project.jobterview.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long>, MessageCustomRepository{

}
