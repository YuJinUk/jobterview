package ssafy.project.jobterview.domain;

import lombok.*;
import ssafy.project.jobterview.dto.ChatDto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"roomChatId", "content"})
public class Chat extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "chat_id")
    private Long chatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    public Chat(Room room, Member member, String content) {
        this.room = room;
        this.member = member;
        this.content = content;
    }

    public ChatDto convertToDto() {
        return ChatDto.builder()
                .chatId(this.chatId)
                .roomId(this.room.getRoomId())
                .memberId(this.member.getMemberId())
                .content(this.content)
                .createdDate(this.getCreatedDate())
                .build();
    }

}
