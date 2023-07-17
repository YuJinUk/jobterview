package ssafy.project.jobterview.domain;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MateId implements Serializable {

    @Column(name = "from_member_id")
    private Long fromMemberId;

    @Column(name = "to_member_id")
    private Long toMemberId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MateId mateId = (MateId) o;
        return Objects.equals(fromMemberId, mateId.fromMemberId) && Objects.equals(toMemberId, mateId.toMemberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromMemberId, toMemberId);
    }
}
