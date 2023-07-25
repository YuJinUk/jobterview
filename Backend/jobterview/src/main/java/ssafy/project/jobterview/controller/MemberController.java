package ssafy.project.jobterview.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.MemberDto;
import ssafy.project.jobterview.dto.UpdatePasswordDto;
import ssafy.project.jobterview.exception.NotFoundException;
import ssafy.project.jobterview.repository.MemberRepository;
import ssafy.project.jobterview.service.EmailService;
import ssafy.project.jobterview.service.MemberService;

import java.util.List;

@Api(value = "회원 API", tags = {"Member"})
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MemberService ms;

    /**
     * 회원 가입 (등록)
     * @param memberDto 회원가입 입력 정보
     * @return
     */
    @PostMapping("/join")
    @ApiOperation(value = "회원 등록", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> join(@RequestBody @ApiParam(value="회원 가입 정보", required = true) MemberDto memberDto) {
        String rawPassword = memberDto.getPassword();
        String encPwd = bCryptPasswordEncoder.encode(rawPassword);
        memberDto.setPassword(encPwd);
        Member member = new Member(memberDto.getEmail(), memberDto.getNickname(), memberDto.getPassword());
        ms.save(member);
//        return ResponseEntity.ok("check");
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    /**
     * 회원 탈퇴 (회원 상태를 탈퇴로 수정, 실제로 DB에서 삭제는 안함)
     * @param memberDto 탈퇴할 회원 정보
     * @return
     */
    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> quit(@RequestBody @ApiParam(value="탈퇴할 회원 정보", required = true) MemberDto memberDto) {
        ms.quit(memberDto.getEmail());
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    /**
     * 비밀번호 수정
     * @param updatePasswordDto 비밀번호 체크 및 수정을 위한 이메일, 기존 비밀번호, 새 비밀번호 정보를 가지는 Dto
     * @return
     */
    @PutMapping
    @ApiOperation(value = "비밀번호 수정", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> updatePassword(@RequestBody @ApiParam(value="비밀번호 수정할 회원 정보", required = true) UpdatePasswordDto updatePasswordDto) {
        Member member = ms.findByEmail(updatePasswordDto.getEmail());
        if(bCryptPasswordEncoder.matches(updatePasswordDto.getPassword(), member.getPassword())){
            member.setPassword(bCryptPasswordEncoder.encode(updatePasswordDto.getNewPassword()));
            ms.save(member);
            return new ResponseEntity<>(updatePasswordDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(updatePasswordDto, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/me")
    @ApiOperation(value = "현재 로그인 한 회원 정보 조회", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> myInfo(@RequestBody @ApiParam(value="현재 로그인 한 회원 정보", required = true) MemberDto memberDto) {
        Member member = ms.findByEmail(memberDto.getEmail());
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
    @GetMapping
    @ApiOperation(value = "회원 검색", notes = "")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "질문 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> searchByNickname(@PageableDefault(page = 0, size = 10,
            sort = "nickname", direction = Sort.Direction.ASC) @ApiParam(value="페이지 정보", required = true) Pageable pageable, @RequestParam @ApiParam(value="검색할 닉네임 키워드", required = true) String keyword) {
        Page<MemberDto> members = ms.findByNicknameContains(pageable, keyword).map(Member::toMemberDto);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Autowired
    private EmailService es;
    @PostMapping("/emailConfirm")
    @ApiOperation(value = "이메일 전송", notes = "")
    public String emailConfirm(@RequestParam String email) throws Exception {
        String confirm = es.sendSimpleMessage(email);
        return confirm;
    }
}