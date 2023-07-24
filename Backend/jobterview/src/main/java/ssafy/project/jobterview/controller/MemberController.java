package ssafy.project.jobterview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.repository.MemberRepository;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MemberRepository memberRepository;


    @PostMapping("/join")
    public ResponseEntity<?> join(Member member) {
        System.out.println(member);
        String rawPassword = member.getPassword();
        String encPwd = bCryptPasswordEncoder.encode(rawPassword);
        member.setPassword(encPwd);
        memberRepository.save(member);

//        return ResponseEntity.ok("check");
        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }



}
