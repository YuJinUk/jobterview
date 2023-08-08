package ssafy.project.jobterview.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteMemberDto {
    private String nickname;
    private String password;
}
