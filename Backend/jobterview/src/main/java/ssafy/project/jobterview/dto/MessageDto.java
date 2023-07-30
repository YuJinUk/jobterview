package ssafy.project.jobterview.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class MessageDto {
    private Long id;
    private String senderNickname;
    private String receiverNickname;
    private String content;
    private LocalDateTime createdDate;
}
