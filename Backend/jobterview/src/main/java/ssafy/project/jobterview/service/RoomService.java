package ssafy.project.jobterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.exception.NotFoundException;

public interface RoomService {

    Room findById(Long roomId);

    Room findByName(String roomName);

    Page<Room> searchByName(String keyword, Pageable pageable);

    Page<Room> findAll(Pageable pageable) throws NotFoundException;
}
