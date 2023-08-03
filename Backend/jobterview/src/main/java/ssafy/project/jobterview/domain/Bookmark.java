package ssafy.project.jobterview.domain;

import lombok.*;
import ssafy.project.jobterview.dto.BookmarkDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Bookmark extends BaseTimeEntity {

    @EmbeddedId
    private BookmarkId bookmarkId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @MapsId("memberId")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @MapsId("roomId")
    private Room room;

    public BookmarkDto convertToDto() {
        return BookmarkDto.builder()
                .roomId(this.room.getRoomId())
                .memberId(this.member.getMemberId())
                .build();
    }
}
