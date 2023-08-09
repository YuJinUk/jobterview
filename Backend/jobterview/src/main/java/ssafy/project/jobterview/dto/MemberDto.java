package ssafy.project.jobterview.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
    private String email;
    private String nickname;
    private String password;
    private String role;
}
