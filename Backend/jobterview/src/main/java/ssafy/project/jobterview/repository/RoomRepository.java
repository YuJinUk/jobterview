package ssafy.project.jobterview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ssafy.project.jobterview.domain.Room;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * 특정 roomName에 해당하는 Room 정보 조회
     *
     * @param roomName 조회할 Room의 roomName
     * @return Optional<Room> 형태로 조회된 Room 정보 반환
     */
    Optional<Room> findByRoomName(String roomName);

    Page<Room> findByRoomNameContains(Pageable pageable, String keyword);
}