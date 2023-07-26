package ssafy.project.jobterview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.service.MessageService;

@Controller
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    // 메세지 작성
    @PostMapping("message")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public String message(@RequestParam("receiver_id") Member receiver_Id,
                          @RequestParam("sender_id")Member sender_id,
                          @RequestParam("content")String content){
        messageService.send(message_id, sender_id,receiver_id,content);
        return "redirect:/message";
    }
    // 메세지 전체 조회
    @GetMapping("message/me")
    public String messageList(Message message){
        List<Message>messages = messageService.findMessages();
        message.addAtriubte("messages", messages);
        return "message/me";
    }
}
