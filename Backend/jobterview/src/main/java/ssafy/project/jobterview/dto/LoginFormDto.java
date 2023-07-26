package ssafy.project.jobterview.dto;

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
