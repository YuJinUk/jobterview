package ssafy.project.jobterview.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ssafy.project.jobterview.domain.Category;
import ssafy.project.jobterview.domain.Question;
import ssafy.project.jobterview.service.ReportService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReportRepositoryTest {
    @Autowired
    ReportService rs;

    @Test
    @BeforeEach
    void insert() {
    }

    @Test
    @DisplayName("1. insert")
    @Order(1)
    void insertTest() {
        assertThat(qs.findById(1L).getCategory()).isEqualTo(Category.VISION);
    }
    @Test
    @DisplayName("2. findAll")
    @Order(2)
    void findAllTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Question> q = qs.findAll(pageable);
        assertThat(q.getTotalElements()).isEqualTo(10L);
        assertThat(q.getTotalPages()).isEqualTo(1);
    }

    @Test
    @DisplayName("3. findByNickname")
    @Order(3)
    void findNicknameTest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("category").ascending());
        Page<Question> q = qs.findAllByCategory(Category.VISION, pageable);
        assertThat(q.getTotalElements()).isEqualTo(2L);
        assertThat(q.getTotalPages()).isEqualTo(1);
    }

    @Test
    @DisplayName("4. delete")
    @Order(4)
    void deleteTest() {
        qs.deleteById(50L);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Question> q = qs.findAll(pageable);
        assertThat(q.getTotalElements()).isEqualTo(9L);
        assertThat(q.getTotalPages()).isEqualTo(1);
    }
}
