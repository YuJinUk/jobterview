package ssafy.project.jobterview.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import ssafy.project.jobterview.dto.MemberDto;
import ssafy.project.jobterview.dto.QuestionDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicInsert
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
    private List<Chat> roomChatList = new ArrayList<>();

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

    @Column(name = "is_active") //nullable = 뺏습니다 있으면 객체 생성 안됨
    @ColumnDefault("1") //이게 있어서 디비 갈때는 자동으로 생길듯 안생기누,,,,
    private Integer isActive; //요거 int=>Integer로 바꿧습니다

    //role 만들기
    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'user'")
    private Role role;

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", receivedMessageList=" + receivedMessageList +
                ", sentMessageList=" + sentMessageList +
                ", roomChatList=" + roomChatList +
                ", answerList=" + answerList +
                ", bookmarkList=" + bookmarkList +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", mailCert=" + mailCert +
                ", refreshToken='" + refreshToken + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public Member(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public static MemberDto toMemberDto(Member m) {
        return MemberDto.builder()
                .email(m.getEmail())
                .nickname(m.getNickname())
                .build();
    }
}

