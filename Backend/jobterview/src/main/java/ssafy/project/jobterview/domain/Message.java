package ssafy.project.jobterview.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import ssafy.project.jobterview.dto.MessageDto;

import javax.persistence.*;

@DynamicInsert
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Message extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private Member receiver;

    private String content;

    @Column(name = "is_read", nullable = false)
    @ColumnDefault("false")
    private boolean isRead;

    @Column(name = "sender_visible", nullable = false)
    @ColumnDefault("true")
    private boolean senderVisible;

    @Column(name = "receiver_visible", nullable = false)
    @ColumnDefault("true")
    private boolean receiverVisible;

    public Message(Long id, Member sender, Member receiver, String content,boolean fromMemberVisible, boolean toMemberVisible){
        this.id=id;
        this.sender=sender;
        this.receiver=receiver;
        this.content=content;
        this.fromMemberVisible=fromMemberVisible;
       this.toMemberVisible=toMemberVisible;


    }


    public MessageDto convertToDto(){
        return MessageDto.builder()
                .id(this.getId())
                .senderNickname(this.getSender().getNickname())
                .receiverNickname(this.getReceiver().getNickname())
                .content(this.getContent())




                .build();
    }

}
