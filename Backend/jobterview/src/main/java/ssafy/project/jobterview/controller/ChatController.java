package ssafy.project.jobterview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.domain.Chat;
import ssafy.project.jobterview.dto.ChatDto;
import ssafy.project.jobterview.dto.SaveChatDto;
import ssafy.project.jobterview.service.ChatService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    /**
     * 채팅 저장
     * 
     * @param saveChatDto 저장할 채팅 정보
     * @return 저장 성공시 HttpStatus.OK 반환
     */
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody SaveChatDto saveChatDto) {
        chatService.save(saveChatDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 모든 채팅 목록 조회
     * 
     * @param pageable 페이징 및 정렬 정보
     * @return Page<ChatDto>형태로 조회된 채팅 목록과 HttpStatus.OK 반환
     */
    @GetMapping
    public ResponseEntity<Page<ChatDto>> getAllChats(
            @PageableDefault(page = 0, size = 50,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(chatService.findAll(pageable).map(Chat::convertToDto), HttpStatus.OK);
    }

    /**
     * 특정 Member의 채팅 목록 조회
     * 
     * @param memberId Member id
     * @param pageable 페이징 및 정렬 정보
     * @return Page<ChatDto>형태로 조회된 채팅 목록과 HttpStatus.OK 반환
     */
    @GetMapping("/member/{memberId}")
    public ResponseEntity<Page<ChatDto>> getMemberChats(
            @PathVariable(name = "memberId") Long memberId,
            @PageableDefault(page = 0, size = 50,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(chatService.findByMemberId(memberId,pageable).map(Chat::convertToDto), HttpStatus.OK);
    }

    /**
     * 특정 Room의 채팅 목록 조회
     *
     * @param roomId Room id
     * @param pageable 페이징 및 정렬 정보
     * @return Page<ChatDto>형태로 조회된 채팅 목록과 HttpStatus.OK 반환
     */
    @GetMapping("/room/{roomId}")
    public ResponseEntity<Page<ChatDto>> getRoomChats(
            @PathVariable(name = "roomId") Long roomId,
            @PageableDefault(page = 0, size = 50,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(chatService.findByRoomId(roomId, pageable).map(Chat::convertToDto), HttpStatus.OK);
    }

    /**
     * 특정 Room의 특정 Member의 채팅 목록 조회
     *
     * @param roomId Room id
     * @param memberId Member id
     * @param pageable 페이징 및 정렬 정보
     * @return Page<ChatDto>형태로 조회된 채팅 목록과 HttpStatus.OK 반환
     */
    @GetMapping("/{roomId}/{memberId}")
    public ResponseEntity<Page<ChatDto>> getRoomAndMemberChats(
            @PathVariable(name = "roomId") Long roomId,
            @PathVariable(name = "memberId") Long memberId,
            @PageableDefault(page = 0, size = 50,
                    sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(chatService.findByRoomAndMember(roomId, memberId, pageable).map(Chat::convertToDto), HttpStatus.OK);
    }
}
