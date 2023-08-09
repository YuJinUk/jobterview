package ssafy.project.jobterview.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.OpenSSLUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.dto.DeleteMemberDto;
import ssafy.project.jobterview.dto.MemberDto;
import ssafy.project.jobterview.dto.UpdatePasswordDto;
import ssafy.project.jobterview.service.EmailService;
import ssafy.project.jobterview.service.MemberService;

@Api(value = "회원 API", tags = {"Member"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberService memberService;
    private final EmailService emailService;

    /**
     * 회원 가입 (등록)
     *
     * @param memberDto 회원가입 입력 정보
     * @return
     */
    @PostMapping("/join")
    @ApiOperation(value = "회원 등록", notes = "")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "질문 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> join(@RequestBody @ApiParam(value = "회원 가입 정보", required = true) MemberDto memberDto) {
        String rawPassword = memberDto.getPassword();
        String encPwd = bCryptPasswordEncoder.encode(rawPassword);
        memberDto.setPassword(encPwd);
        Member member = new Member(memberDto.getEmail(), memberDto.getNickname(), memberDto.getPassword());
        //맴버 저장
        Member saveMember = memberService.save(member);
        //저장된 맴버 반환
        return new ResponseEntity<>(saveMember, HttpStatus.OK);
    }

    @GetMapping("/nicknameCheck")
    @ApiOperation(value = "닉네임이 일치하는 회원이 있다면 0 반환", notes = "")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "질문 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> checkByNickname(@ApiParam(value = "중복 닉네임 체크", required = true) @RequestParam String nickname) {
        Member member = null;
        try {
            member = memberService.findByNickname(nickname);
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        }
    }

    @GetMapping("/emailCheck")
    @ApiOperation(value = "이메일이 일치하는 회원이 있다면 0 반환", notes = "")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "질문 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> checkByEmail(@ApiParam(value = "중복 이메일 체크", required = true) @RequestParam String email) {
        Boolean check;
        Member member = null;
        try {
            member = memberService.findByEmail(email);
            return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(1, HttpStatus.OK);
        }
    }

    /**
     * 회원 탈퇴 (회원 상태를 탈퇴로 수정, 실제로 DB에서 삭제는 안함)
     *
     * @param memberDto 탈퇴할 회원 정보
     * @return
     */
    @PutMapping("/withdraw")
    @ApiOperation(value = "회원 탈퇴", notes = "")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "질문 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> quit(@RequestBody @ApiParam(value = "탈퇴할 회원 정보", required = true) DeleteMemberDto deleteMemberDto) {
        Member member = memberService.findByNickname(deleteMemberDto.getNickname());
        // 입력 받은 비밀번호가 같다면
        if(bCryptPasswordEncoder.matches(deleteMemberDto.getPassword(),member.getPassword())){
            memberService.quit(member.getEmail());
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);}
        // 입력 받은 비밀번호가 다르다면
        else if(!bCryptPasswordEncoder.matches(deleteMemberDto.getPassword(),member.getPassword())){
            return new ResponseEntity<>("Wrong",HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>("FAIL",HttpStatus.NOT_ACCEPTABLE);
        }
    }
    /**
     * 비밀번호 수정
     *
     * @param updatePasswordDto 비밀번호 체크 및 수정을 위한 이메일, 기존 비밀번호, 새 비밀번호 정보를 가지는 Dto
     * @return
     */
    @PutMapping
    @ApiOperation(value = "비밀번호 수정", notes = "")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "질문 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> updatePassword(@RequestBody @ApiParam(value = "비밀번호 수정할 회원 정보", required = true) UpdatePasswordDto updatePasswordDto) {
        Member member = memberService.findByNickname(updatePasswordDto.getNickname());
        if (bCryptPasswordEncoder.matches(updatePasswordDto.getPassword(), member.getPassword())) {
            member.insertPassword(bCryptPasswordEncoder.encode(updatePasswordDto.getNewPassword()));
            memberService.save(member);
            return new ResponseEntity<>(updatePasswordDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(updatePasswordDto, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/resetPassword")
    @ApiOperation(value = "비밀번호 찾기 후 비밀번호 재설정", notes = "")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "질문 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> resetPassword(@RequestBody @ApiParam(value = "비밀번호 수정할 회원 정보", required = true) UpdatePasswordDto updatePasswordDto) {
        Member member = memberService.findByEmail(updatePasswordDto.getEmail());
        member.insertPassword(bCryptPasswordEncoder.encode(updatePasswordDto.getNewPassword()));
        memberService.save(member);
        return new ResponseEntity<>(updatePasswordDto, HttpStatus.OK);
    }



    @GetMapping("/me")
    @ApiOperation(value = "현재 로그인 한 회원 정보 조회", notes = "")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "질문 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> myInfo(@RequestBody @ApiParam(value = "현재 로그인 한 회원 정보", required = true) MemberDto memberDto) {
        Member member = memberService.findByEmail(memberDto.getEmail());
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "회원 검색", notes = "")
    @ApiResponses({@ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "질문 없음"), @ApiResponse(code = 500, message = "서버 오류")})
    public ResponseEntity<?> searchByNickname(@PageableDefault(page = 0, size = 10, sort = "nickname", direction = Sort.Direction.ASC) @ApiParam(value = "페이지 정보", required = true) Pageable pageable, @RequestParam @ApiParam(value = "검색할 닉네임 키워드", required = true) String keyword) {
        Page<MemberDto> members = null;
        if(keyword.trim().length() == 0) {
            members = memberService.getAllActiveMember(pageable).map(Member::toMemberDto);
        } else {
            members = memberService.findByNicknameContainsAndRole(pageable, keyword).map(Member::toMemberDto);
        }
        System.out.println(members.getContent().size());

        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping("/emailconfirm")
    @ApiOperation(value = "이메일 전송", notes = "")
    public ResponseEntity<?> emailConfirm(@RequestParam String email) throws Exception {
        String confirm = emailService.sendSimpleMessage(email);
        return new ResponseEntity<>(confirm, HttpStatus.OK);
    }

    @PutMapping("/emailauth")
    @ApiOperation(value = "이메일 인증", notes = "")
    public ResponseEntity<?> emailAuth(@RequestParam String email, @RequestParam String code) throws Exception {
        System.out.println(email + " " + code);
        memberService.emailAuth(email, code);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @PostMapping("passwordEmailConfirm")
    @ApiOperation(value = "패스워드 이메일 전송", notes = "")
        public ResponseEntity<?> passwordEmailConfirm(@RequestParam String email) throws Exception {
            String confirm = emailService.sendPasswordMessage(email);
            return new ResponseEntity<>(confirm, HttpStatus.OK);
        }


        /**
         * 전체 회원 목록 조회
         *
         * @param pageable 페이징 및 정렬 정보
         * @return 전체 회원 목록
         */
    @GetMapping("/list")
    @ApiOperation(value = "전체 회원 목록 조회")
    public ResponseEntity<Page<MemberDto>> findAllMember(@PageableDefault(page = 0, size = 10,
            sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(memberService.getAllActiveMember(pageable).map(Member::toMemberDto), HttpStatus.OK);
    }
}