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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("'ROLE_UNVERIFIED'")
    private Role role;

    @Column
    private String authCode;

    public Member(String email, String nickname, String password) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    public static MemberDto toMemberDto(Member m) {
        return MemberDto.builder()
                .email(m.getEmail())
                .nickname(m.getNickname())
                .role(m.role.toString())
                .build();
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", receivedMessageList=" + receivedMessageList +
                ", sentMessageList=" + sentMessageList +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
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

    public void setAuthCode(String code) {
        this.authCode = code;
    }

    public void changeNickname(String nickname){
        this.nickname = nickname;
    }
}