package ssafy.project.jobterview.domain;

import lombok.*;
import ssafy.project.jobterview.dto.MateDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mate extends BaseTimeEntity {

    @EmbeddedId
    private MateId mateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_member_id")
    @MapsId("fromMemberId")
    private Member fromMember;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_member_id")
    @MapsId("toMemberId")
    private Member toMember;


    private Mate toMateEntity(MateDto matedto){
        return Mate.builder()

    }
}
