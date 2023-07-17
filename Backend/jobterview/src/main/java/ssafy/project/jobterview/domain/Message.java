package ssafy.project.jobterview.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.time.LocalDateTime;

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

    @Column(name = "from_member_visible", nullable = false)
    @ColumnDefault("true")
    private boolean fromMemberVisible;

    @Column(name = "to_member_visible", nullable = false)
    @ColumnDefault("true")
    private boolean toMemberVisible;

}
