package ssafy.project.jobterview.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class RoomDto {
    private Long roomId;
    private String roomPassword;
    private int nowMember;
    private int maxMember;
    private String roomName;
    private LocalDateTime createdDate;
}
