package ssafy.project.jobterview.dto;

import lombok.*;
import ssafy.project.jobterview.domain.MateId;
import ssafy.project.jobterview.domain.Member;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class MateDto {
    private MateId mateId;
    private String fromMemberNickname;
    private String toMemberNickname;
}