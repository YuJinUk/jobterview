package ssafy.project.jobterview.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.domain.Report;
import ssafy.project.jobterview.dto.ReportDto;
import ssafy.project.jobterview.service.ReportService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReportRepositoryTest {
    @Autowired
    ReportService rs;

    @Autowired
    MemberRepository mr;


    @BeforeEach
    void insert() {
        Member reporter = new Member("1@gmail.com", "reporter", "1234");
        Member reported = new Member("2@naver.com", "reported", "1234");
        mr.save(reporter);
        mr.save(reported);
        ReportDto report1 = new ReportDto("reporter", "reported", "reporter -> reported");
        ReportDto report2 = new ReportDto("reported", "reporter", "reported -> reporter");
        rs.save(report1);
        rs.save(report2);

//        Pageable pageable = PageRequest.of(0, 10, Sort.by("reportId").ascending());
//        Page<Report> reports = rs.findAll(pageable);
//        System.out.println("findAll 데이터 개수 : " + reports.getTotalElements());
//        assertThat(reports.getTotalElements()).isEqualTo(2L);
//        assertThat(reports.getTotalPages()).isEqualTo(1);
//
//        Page<Report> reports2 = rs.findAllByReportedMember("reported", pageable);
//        assertThat(reports2.getTotalElements()).isEqualTo(1L);
//        assertThat(reports2.getContent().get(0).getReportedMember().getNickname()).isEqualTo("reported");
//        System.out.println("findAllByReported 결과 : " + reports2.getContent().get(0).getReportedMember().getNickname());
//
//        ReportDto deleteReport = new ReportDto("reported", "reporter", "reported -> reporter");
//        rs.delete(deleteReport);
//        System.out.println("delete 결과 : " + rs.findAll(pageable).getContent().size());
//        assertThat(rs.findAll(pageable).getContent().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("1. findAll")
    @Order(1)
    void findAllTest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("reportId").ascending());
        Page<Report> reports = rs.findAll(pageable);
        assertThat(reports.getTotalElements()).isEqualTo(2L);
        assertThat(reports.getTotalPages()).isEqualTo(1);
    }

    @Test
    @DisplayName("2. findAllByReportedMember")
    @Order(2)
    void findAllByReportedTest() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("reportId").ascending());
        Page<Report> reports = rs.findAllByReportedMember("reported", pageable);
        assertThat(reports.getTotalElements()).isEqualTo(1L);
        assertThat(reports.getContent().get(0).getReportedMember().getNickname()).isEqualTo("reported");
    }

    @Test
    @DisplayName("3. Delete")
    @Order(3)
    void deleteTest() {
        ReportDto deleteReport = new ReportDto("reported", "reporter", "reported -> reporter");
        rs.delete(deleteReport);
        Pageable pageable = PageRequest.of(0, 10, Sort.by("reportId").ascending());
        assertThat(rs.findAll(pageable).getContent().size()).isEqualTo(1);
    }
}
