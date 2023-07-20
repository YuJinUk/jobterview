package ssafy.project.jobterview.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class SaveChatDto {
    private Long memberId;
    private Long roomId;
    private String content;
}
