package ssafy.project.jobterview.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import ssafy.project.jobterview.dto.QuestionDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long questionId;

    @OneToMany(mappedBy = "question")
    private List<Answer> answerList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "selected_cnt")
    @ColumnDefault("0")
    private Long selectedCnt;

    @Column(nullable = false)
    private String content;

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Question(Category category, String content) {
        this.category = category;
        this.content = content;
    }

    private QuestionDto toQuestionDto(Question q) {
        return QuestionDto.builder().
                questionId(q.getQuestionId()).
                category(q.getCategory().name()).
                content(q.getContent()).
                build();
    }
}
