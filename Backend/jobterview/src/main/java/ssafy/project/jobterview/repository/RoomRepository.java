package ssafy.project.jobterview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project.jobterview.domain.Bookmark;
import ssafy.project.jobterview.domain.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomName(String roomName);
}
