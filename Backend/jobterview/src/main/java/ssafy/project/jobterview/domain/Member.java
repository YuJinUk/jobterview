package ssafy.project.jobterview.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import ssafy.project.jobterview.dto.MemberDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'ROLE_NEW'")
    private Role role;

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
                '}';
    }

    public void insertPassword(String password) {
        this.password = password;
    }

    /**
     * 맴버 권한 변환
     */
    public void changeRole(Role role) {
        this.role = role;
    }
}

