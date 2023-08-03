package ssafy.project.jobterview.dto;

import lombok.*;
import ssafy.project.jobterview.domain.Chat;
import ssafy.project.jobterview.domain.RoomStatus;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class RoomDto {
    private Long roomId;
    private List<Chat> roomChatList;
    private RoomStatus roomStatus;
    private int nowMember;
    private int maxMember;
    private String roomName;
    private LocalDateTime createdDate;
}
