package ssafy.project.jobterview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.dto.RoomDto;
import ssafy.project.jobterview.service.RoomService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    /**
     * 특정 roomId에 해당하는 Room 정보 조회
     * 
     * @param roomId 조회할 Room의 식별자
     * @return ResponseEntity<RoomDto> 형태로 조회된 Room 정보 반환
     */
    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long roomId) {
        // roomId에 해당하는 Room을 조회하고, RoomDto로 변환하여 반환
        return new ResponseEntity<>(roomService.findById(roomId).convertToDto(), HttpStatus.OK);
    }

    /**
     * 특정 roomName에 해당하는 Room 목록 조회
     *
     * @param roomName 조회할 Room의 이름
     * @return ResponseEntity<RoomDto> 형태로 조회된 Room 정보 반환
     */
    @GetMapping("/{roomName}")
    public ResponseEntity<RoomDto> getRoomByName(@PathVariable String roomName) {
        // roomName에 해당하는 Room을 조회하고, RoomDto로 변환하여 반환
        return new ResponseEntity<>(roomService.findByName(roomName).convertToDto(), HttpStatus.OK);
    }

    /**
     * 특정 keyword를 roomName에 포함하는 Room 목록 조회
     * 
     * @param keyword roomName이 포함하는지 조회할 검색어 
     * @param pageable 페이징 및 정렬 정보
     * @return ResponseEntity<Page<RoomDto>> 형태로 페이징된 Room 목록 반환
     */
    @GetMapping("/search")
    public ResponseEntity<Page<RoomDto>> searchRoomsByName(
            @RequestParam("keyword") String keyword,
            @PageableDefault(page = 0, size = 10,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Room> roomPage = roomService.searchByName(keyword, pageable);
        Page<RoomDto> roomDtoPage = roomPage.map(Room::convertToDto);
        return new ResponseEntity<>(roomDtoPage, HttpStatus.OK);
    }


    /**
     * 페이징된 Room 목록 조회
     * 
     * @param pageable 페이징 및 정렬 정보
     * @return ResponseEntity<Page<RoomDto>> 형태로 페이징된 Room 목록 반환
     */
    @GetMapping
    public ResponseEntity<Page<RoomDto>> getRoomList(
            @PageableDefault(page = 0, size = 10,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        // 페이징된 Room 목록 조회
        Page<Room> roomPage = roomService.findAll(pageable);
        // Room 객체를 RoomDto로 변환하여 페이징된 RoomDto 목록 생성
        Page<RoomDto> roomDtoPage = roomPage.map(Room::convertToDto);
        return new ResponseEntity<>(roomDtoPage, HttpStatus.OK);
    }
}
