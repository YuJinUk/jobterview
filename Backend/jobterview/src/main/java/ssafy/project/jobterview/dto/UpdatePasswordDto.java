package ssafy.project.jobterview.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePasswordDto {
    private String nickname;
    private String password;
    private String newPassword;
}
