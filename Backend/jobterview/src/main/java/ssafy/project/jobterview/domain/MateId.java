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
public class MateId implements Serializable {

    @Column(name = "from_member_id")
    private Long fromMemberId;

    @Column(name = "to_member_id")
    private Long toMemberId;
}
