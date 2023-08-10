package ssafy.project.jobterview.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePasswordDto {
    private String email;
    private String nickname;
    private String password;
    private String newPassword;
    private String code;
}
