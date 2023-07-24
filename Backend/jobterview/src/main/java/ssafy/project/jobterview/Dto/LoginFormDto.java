package ssafy.project.jobterview.Dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class LoginFormDto {
    private String email;
    private String nickname;
}
