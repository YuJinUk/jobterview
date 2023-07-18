package ssafy.project.jobterview.dto;

import lombok.*;
import ssafy.project.jobterview.domain.Question;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {
    private Long questionId;
    private String category;
    private String content;
}
