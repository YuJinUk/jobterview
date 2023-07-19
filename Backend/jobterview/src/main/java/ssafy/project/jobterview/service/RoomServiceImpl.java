package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.exception.NotFoundException;
import ssafy.project.jobterview.repository.RoomQueryRepository;
import ssafy.project.jobterview.repository.RoomRepository;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final RoomQueryRepository roomQueryRepository;

    @Override
    public Room findById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundException("해당 id의 방이 존재하지 않습니다."));
    }

    @Override
    public Room findByName(String roomName) {
        return roomRepository.findByRoomName(roomName)
                .orElseThrow(() -> new NotFoundException("해당 이름의 방이 존재하지 않습니다."));
    }

    @Override
    public Page<Room> searchByName(String keyword, Pageable pageable) {
        return roomQueryRepository.searchByName(keyword, pageable);
    }

    @Override
    public Page<Room> findAll(Pageable pageable) throws NotFoundException{
        Page<Room> roomList = roomRepository.findAll(pageable);
        if(roomList.isEmpty()) {
            throw new NotFoundException("생성된 방이 존재하지 않습니다.");
        }
        return roomList;
    }
}
