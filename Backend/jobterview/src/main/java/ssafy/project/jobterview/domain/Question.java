package ssafy.project.jobterview.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    @OneToMany(mappedBy = "question")
    private List<Answer> answerList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    private String content;

    @Column(name = "selected_cnt")
    @ColumnDefault("0")
    private Long selectedCnt;
}
