package ssafy.project.jobterview.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ssafy.project.jobterview.dto.MateDto;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Mate(MateId mateId, Member fromMember, Member toMember){
        this.mateId=mateId;
        this.fromMember=fromMember;
        this.toMember=toMember;
    }
    public MateDto convertToDto(){
        return MateDto.builder()
                .mateId(this.getMateId())
                .fromMember(this.getFromMember())
                .toMember(this.getToMember())
                .build();
    }



}
