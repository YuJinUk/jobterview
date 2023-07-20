package ssafy.project.jobterview.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Message;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long>, MessageCustomRepository{

}
