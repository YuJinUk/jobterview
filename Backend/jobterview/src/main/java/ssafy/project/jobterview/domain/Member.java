package ssafy.project.jobterview.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "fromMember")
    private List<Message> sentMessage = new ArrayList<>();

    @OneToMany(mappedBy = "toMember")
    private List<Message> receivedMessages = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<RoomChat> roomChatList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Answer> answerList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "reportedMember")
    private List<Report> reportedList = new ArrayList<>();

    @OneToMany(mappedBy = "reporter")
    private List<Report> reportList = new ArrayList<>();

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    private String intro;

    @Embedded
    private EmailVerify emailVerify;

    @Column(name = "is_active", nullable = false)
    @ColumnDefault("1")
    private int isActive;
}
