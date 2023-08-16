package ssafy.project.jobterview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project.jobterview.domain.Message;

public interface MessageRepository extends JpaRepository<Message, Long>, MessageCustomRepository {

    @EntityGraph(attributePaths = {"sender","receiver"})
    Page<Message> findAll(Pageable pageable);
}