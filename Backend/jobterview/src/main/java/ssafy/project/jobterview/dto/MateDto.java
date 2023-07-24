package ssafy.project.jobterview.dto;

import lombok.*;
import ssafy.project.jobterview.domain.MateId;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Mate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class MateDto {
    private MateId mateId;
    private Member fromMember;
    private Member toMember;
}