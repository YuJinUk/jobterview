package ssafy.project.jobterview.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.domain.Message;
import ssafy.project.jobterview.dto.MessageDto;
import ssafy.project.jobterview.service.MessageService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/message")
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    @ApiOperation(value = "전체 메세지 목록")
    public ResponseEntity<Page<MessageDto>> getAllMessages(
            @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<MessageDto> messageDtoPage = messageService.findAllWithPaging(pageable).map(Message::convertToDto);
        return new ResponseEntity<>(messageDtoPage, HttpStatus.OK);
    }

    // 받은 메세지 목록
    @GetMapping("/me")
    @ApiOperation(value = "받은 메세지 목록")
    public ResponseEntity<Page<MessageDto>> getFromMessageByNickname(@PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String nickname) {
        Page<Message> messagePage = messageService.findAllByFromMemberVisibleAndReceiver(pageable, nickname);
        Page<MessageDto> messageDtoPage = messagePage.map(Message::convertToDto);
        return new ResponseEntity<>(messageDtoPage, HttpStatus.OK);
    }

    // 보낸 메세지 목록
    @GetMapping("/send")
    @ApiOperation(value = "보낸 메세지 목록")
    public ResponseEntity<Page<MessageDto>> getToMessageByNickname(@PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String nickname) {
        Page<Message> messagePage = messageService.findAllByToMemberVisibleAndSender(pageable, nickname);
        Page<MessageDto> messageDtoPage = messagePage.map(Message::convertToDto);
        return new ResponseEntity<>(messageDtoPage, HttpStatus.OK);
    }

    // 메세지 작성
    @PostMapping
    @ApiOperation(value = "메세지 작성")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto mDto) {
        messageService.save(mDto);
        return new ResponseEntity<>(mDto, HttpStatus.OK);
    }

    // 메세지 상세보기 (열람)
    @GetMapping("/detail/{id}")
    @ApiOperation(value = "메세지 상세 보기")
    public ResponseEntity<?> readMessage(@PathVariable Long id) {
        Message m = messageService.read(id);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    // 받은 메세지 삭제
    @PutMapping("/from/{id}")
    @ApiOperation(value = "받은 메세지 삭제")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "채팅 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public void fromMessageDelete(@PathVariable Long id) {
        messageService.receiveMessageDelete(id);
    }

    // 보낸 메세지 삭제
    @PutMapping("/to/{id}")
    @ApiOperation(value = "보낸 메세지 삭제")
    public void toMessageDelete(@PathVariable Long id) {
        messageService.sendMessageDelete(id);
    }
}
