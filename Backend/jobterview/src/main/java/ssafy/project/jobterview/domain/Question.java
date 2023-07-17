package ssafy.project.jobterview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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
}
