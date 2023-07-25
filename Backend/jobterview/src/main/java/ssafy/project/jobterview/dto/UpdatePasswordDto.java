package ssafy.project.jobterview.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePasswordDto {
    private String email;
    private String password;
    private String newPassword;
}
