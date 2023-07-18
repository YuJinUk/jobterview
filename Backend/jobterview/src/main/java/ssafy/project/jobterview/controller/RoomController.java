package ssafy.project.jobterview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.project.jobterview.dto.RoomDto;
import ssafy.project.jobterview.service.RoomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable Long roomId) {
        return new ResponseEntity<>(roomService.findById(roomId).toRoomDto(), HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<Page<RoomDto>> getRoomList(
//            @PageableDefault(page = 0, size = 10,
//                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
//        roomService.findAll(pageable);
//    }
}
