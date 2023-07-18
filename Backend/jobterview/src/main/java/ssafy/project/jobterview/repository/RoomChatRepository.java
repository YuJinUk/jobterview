package ssafy.project.jobterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project.jobterview.domain.Bookmark;
import ssafy.project.jobterview.domain.RoomChat;

public interface RoomChatRepository extends JpaRepository<RoomChat, Long> {
}
