package ssafy.project.jobterview.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDto {
    private String reporterNickname;
    private String reportedNickname;
    private String reason;
}
