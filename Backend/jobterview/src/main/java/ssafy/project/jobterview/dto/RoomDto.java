package ssafy.project.jobterview.dto;

import lombok.*;
import ssafy.project.jobterview.domain.Room;
import ssafy.project.jobterview.domain.RoomChat;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class RoomDto {
    private Long roomId;
    private List<RoomChat> roomChatList;
    private boolean inMeeting;
    private int nowMember;
    private int maxMember;
    private String roomName;
    private LocalDateTime createdDate;
}
