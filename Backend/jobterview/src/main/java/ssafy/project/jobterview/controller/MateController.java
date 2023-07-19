package ssafy.project.jobterview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.project.jobterview.domain.Mate;
import ssafy.project.jobterview.domain.MateId;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.service.MateService;


//        메이트 등록	/mate/{nickname}	POST
//        메이트 해제	/mate/{nickname}	DELETE
//        메이트 조회	/mate/me	GET

@RestController
@RequiredArgsConstructor
public class MateController {
    private final MateService mateService;
    private final MemberSerivce memberService;


    @PostMapping("/mate/{nickname}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<String> addMate(@PathVariable String nickname){
        Member loginMember = new Member();
        loginMember.setEmail("hello@test.com");
        loginMember.setNickname("test");
        Mate m = new Mate();
        MateId mateId = new MateId();








    }

    }


