package ssafy.project.jobterview.dto;


import lombok.*;
import ssafy.project.jobterview.domain.MailCert;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private String email;
    private String nickname;
    private String password;
    private boolean mailAuth;
    private int isActive;
}
