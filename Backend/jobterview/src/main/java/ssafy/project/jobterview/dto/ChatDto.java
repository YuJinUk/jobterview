package ssafy.project.jobterview.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class ChatDto {
    private Long chatId;
    private Long roomId;
    private Long memberId;
    private String content;
    private LocalDateTime createdDate;
}
