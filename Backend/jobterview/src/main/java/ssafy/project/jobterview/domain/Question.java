package ssafy.project.jobterview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import ssafy.project.jobterview.dto.QuestionDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id")
    private Long questionId;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "selected_cnt")
    @ColumnDefault("0")
    private Long selectedCnt;

    @Column(nullable = false)
    private String content;

    public Question(Category category, String content) {
        this.category = category;
        this.content = content;
    }

    public Question(Long questionId, Category category, String content) {
        this.questionId = questionId;
        this.category = category;
        this.content = content;
    }

    public static QuestionDto toQuestionDto(Question q) {
        return QuestionDto.builder().
                questionId(q.getQuestionId()).
                category(q.getCategory().name()).
                content(q.getContent()).
                build();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
