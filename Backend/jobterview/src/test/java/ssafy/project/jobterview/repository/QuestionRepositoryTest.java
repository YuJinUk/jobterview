package ssafy.project.jobterview.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Question;
import ssafy.project.jobterview.service.QuestionService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionRepositoryTest {
    @Autowired
    QuestionService qs;

    @Test
    @BeforeEach
    void insert() {
        System.out.println(qs.save(new Question(Category.VISION, "1. 비전")));
        qs.save(new Question(Category.ADAPT, "2. 적응력"));
        qs.save(new Question(Category.VALUES, "3. 가치관"));
        qs.save(new Question(Category.PRESSURE, "4. 압박"));
        qs.save(new Question(Category.JOB, "5. 직무"));
        qs.save(new Question(Category.VISION, "6. 비전"));
        qs.save(new Question(Category.ADAPT, "7. 적응력"));
        qs.save(new Question(Category.VALUES, "8. 가치관"));
        qs.save(new Question(Category.PRESSURE, "9. 압박"));
        qs.save(new Question(Category.JOB, "10. 직무"));
    }

    @Test
    @DisplayName("1. insert")
    @Order(1)
    void insertTest() {
        assertThat(qs.findById(1L).getCategory()).isEqualTo(Category.VISION);
    }

    @Test
    @DisplayName("2. update")
    @Order(2)
    void updateTest() {
        Question selected = qs.findById(19L);
        selected.setCategory(Category.VISION);
        assertThat(qs.findById(19L).getCategory()).isEqualTo(Category.VISION);
    }

    @Test
    @DisplayName("3. findAll")
    @Order(3)
    void findAllTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Question> q = qs.findAllWithPaging(pageable);
        assertThat(q.getTotalElements()).isEqualTo(10L);
        assertThat(q.getTotalPages()).isEqualTo(1);
    }

    @Test
    @DisplayName("4. category")
    @Order(4)
    void findCategoryTest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("category").ascending());
        Page<Question> q = qs.findAllByCategory(Category.VISION, pageable);
        assertThat(q.getTotalElements()).isEqualTo(2L);
        assertThat(q.getTotalPages()).isEqualTo(1);
    }

    @Test
    @DisplayName("5. delete")
    @Order(5)
    void deleteTest() {
        qs.deleteById(50L);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Question> q = qs.findAllWithPaging(pageable);
        assertThat(q.getTotalElements()).isEqualTo(9L);
        assertThat(q.getTotalPages()).isEqualTo(1);
    }

    @Test
    @DisplayName("6. random")
    @Order(6)
    void randomTest() {
        int count = 4;
        List<Question> q = qs.findByRandom(4);
        for (Question que : q) {
            System.out.println(que.getContent());
        }
        assertThat(q.size()).isEqualTo(4);
    }
}
