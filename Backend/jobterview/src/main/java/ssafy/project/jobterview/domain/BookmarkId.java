package ssafy.project.jobterview.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class BookmarkId implements Serializable {

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "room_id")
    private Long roomId;
}
