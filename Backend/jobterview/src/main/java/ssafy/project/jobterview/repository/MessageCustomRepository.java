package ssafy.project.jobterview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import ssafy.project.jobterview.domain.Message;

public interface MessageCustomRepository {

    Page<Message> findAllByFromMemberVisibleAndReceiver(Pageable pageable, String nickname);

    Page<Message> findAllByToMemberVisibleAndSender(Pageable pageable, String nickname);
}
