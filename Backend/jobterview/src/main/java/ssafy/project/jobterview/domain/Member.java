package ssafy.project.jobterview.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @OneToMany(mappedBy = "sender")
    private List<Message> receivedMessageList = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Message> sentMessageList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<RoomChat> roomChatList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Answer> answerList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "mail_cert_id")
    private MailCert mailCert;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("1")
    private int isActive;
    public Member(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

}
