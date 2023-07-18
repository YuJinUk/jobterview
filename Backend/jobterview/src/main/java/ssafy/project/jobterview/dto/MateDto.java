package ssafy.project.jobterview.dto;

import lombok.*;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.MateId;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@ToString
public class MateDto {
 // private 으로 가져와서
    private String from_nickcname
    private String to_nickname;

    private Mate toMateEntity(MateDto matedto){
        return Mate.builder()
                .from_nickname(matedto.from_nickcname)
                .to_nickcname(matedto.to_nickname)
                .build();

    }

}
