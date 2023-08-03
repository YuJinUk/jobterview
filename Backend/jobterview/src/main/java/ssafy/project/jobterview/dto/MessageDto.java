package ssafy.project.jobterview.dto;

import lombok.*;

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
    private String createdDate;
}
