package ssafy.project.jobterview.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MessageDto {

    private Long id;
    private String senderNickname;
    private String receiverNickname;
    private String content;
    private String createdDate;

    public MessageDto(String senderNickname, String receiverNickname, String content) {
        this.senderNickname = senderNickname;
        this.receiverNickname = receiverNickname;
        this.content = content;
    }
}
