package ssafy.project.jobterview;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import ssafy.project.jobterview.domain.*;


import ssafy.project.jobterview.dto.MateDto;
import ssafy.project.jobterview.repository.MateRepository;
import ssafy.project.jobterview.repository.MemberRepository;
import ssafy.project.jobterview.service.MateService;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("메이트 API 테스트")
class MateServiceImplTest {

    static Member m1, m2, m3, m4, m5, m6;
    static MateDto mate1, mate2, mate3, mateDto;
    static MateId mateId;
    @Autowired
    EntityManager entityManager;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MateService mateService;
    @Autowired
    MateRepository mateRepository;

    @BeforeEach
    void setUp() {
        Member member1 = new Member("jth1234@naver.com", "태희", "123");
        Member member2 = new Member("pdk1234@naver.com", "대균", "123");
        Member member3 = new Member("ksc1234@naver.com", "수창", "123");
        Member member4 = new Member("lsw1234@naver.com", "상우", "123");
        Member member5 = new Member("yjw1234@naver.com", "진욱", "123");
        Member member6 = new Member("lkh1234@naver.com", "경호", "123");

        m1 = memberRepository.save(member1);
        m2 = memberRepository.save(member2);
        m3 = memberRepository.save(member3);
        m4 = memberRepository.save(member4);
        m5 = memberRepository.save(member5);
        m6 = memberRepository.save(member6);

        MateId mateId = new MateId(m1.getMemberId(), m2.getMemberId());
        MateDto mateDto = new MateDto(mateId, member1, member2);
        mateService.save(mateDto);
    }

    @Test
    @Order(2)
    @DisplayName("메이트 추가 테스트 및 전체 조회")
    void save() {
        List<Mate> mateList = mateRepository.findAll();
        assertEquals(1, mateList.size());
    }

    @Test
    @Order(3)
    @DisplayName("메이트 취소 테스트")
    void delete() {

        // 할 때 위에 setup에 save 하는 부분 주석하고 해야함
        MateId mateId = new MateId(m1.getMemberId(), m2.getMemberId());
        MateDto mateDto = new MateDto(mateId, m1, m2);
        mateService.save(mateDto);
        mateService.delete(mateDto);
        List<Mate> mateList = mateRepository.findAll();
        assertEquals(0, mateList.size());
    }

    @Test
    @Order(1)
    @DisplayName("메이트 리스트 조회 테스트")
    void findAllbyMate() {
        entityManager.flush();
        int page = 0;
        int size = 10;
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Mate> matePage = mateService.findAllByMate(pageable, "태희");
        assertEquals(1, matePage.getContent().size());

    }
}

