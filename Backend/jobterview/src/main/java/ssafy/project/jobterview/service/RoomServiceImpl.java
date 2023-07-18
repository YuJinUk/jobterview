package ssafy.project.jobterview.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.exception.NotFoundException;
import ssafy.project.jobterview.repository.RoomRepository;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;

    /**
     * 해당 roomId의 방 조회
     */
    @Override
    public Room findById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new NotFoundException("해당 방이 존재하지 않습니다."));
    }

    /**
     * 해당 roomName의 방 조회
     */
    @Override
    public Room findByName(String roomName) {
        return roomRepository.findByRoomName(roomName)
                .orElseThrow(() -> new NotFoundException("해당 방이 존재하지 않습니다."));
    }

    /**
     * 페이징과 정렬기준으로 방 리스트를 조회
     */
    @Override
    public Page<Room> findAll(Pageable pageable) throws NotFoundException{
        Page<Room> roomList = roomRepository.findAll(pageable);
        if(roomList.isEmpty()) {
            throw new NotFoundException("방이 존재하지 않습니다.");
        }
        return roomList;
    }
}
