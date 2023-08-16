package ssafy.project.jobterview.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import ssafy.project.jobterview.dto.RoomDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Room extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_password", nullable = false)
    private String roomPassword;

    @Column(name = "now_member", nullable = false)
    @ColumnDefault("1")
    private int nowMember;

    @Column(name = "max_member", nullable = false)
    @ColumnDefault("1")
    private int maxMember;

    @Column(name = "room_name",unique = true)
    private String roomName;

    public Room(String roomName, int maxMember) {
        this.roomName = roomName;
        this.maxMember = maxMember;
    }

    /**
     * Room 객체를 RoomDto 형태로 변환
     *
     * @return 변환된 RoomDto 객체
     */
    public RoomDto convertToDto() {
        return RoomDto.builder().roomId(this.getRoomId())
                .roomName(this.getRoomName())
                .nowMember(this.getNowMember())
                .maxMember(this.getMaxMember())
                .roomPassword(this.getRoomPassword())
                .createdDate(this.getCreatedDate())
                .build();
    }
}