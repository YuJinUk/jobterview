package ssafy.project.jobterview.dto;

import lombok.*;
import ssafy.project.jobterview.domain.Member;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class MessageDto {
    private Long id;
    private Member sender;
    private Member receiver;
    private String content;
    private boolean isRead;
    private boolean fromMemberVisible;
    private boolean toMemberVisible;
}
