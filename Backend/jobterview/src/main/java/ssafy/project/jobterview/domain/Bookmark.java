package ssafy.project.jobterview.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    public void addBookmark(Member member, Room room) {
        this.member = member;
        this.room = room;
        
        //해당 맴버에 북마크 추가
        member.getBookmarkList().add(this);
    }
}
