package ssafy.project.jobterview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.project.jobterview.service.RoomChatService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class RoomChatController {

    private final RoomChatService roomChatService;
}
