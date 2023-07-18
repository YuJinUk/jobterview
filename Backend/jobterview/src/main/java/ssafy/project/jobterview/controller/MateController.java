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

//    public ResponseEntity<?> (@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginPostReq loginInfo) {


//        User user = userService.getUserByUserId(userId);
//        if(passwordEncoder.matches(password, user.getPassword())) {
//            return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(userId)));
//        }
//        return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Password", null));
   // 메이트 추가
    @PostMapping("/mate/{nickname}")
    public ResponseEntity<String> addMate(@PathVariable String nickname){
        Member loginMember = new Member();
        loginMember.setEmail("hello@test.com");
        loginMember.setNickname("test");
        Mate m = new Mate();
        MateId mateId = new MateId();








    }

    }


