package ssafy.project.jobterview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.repository.MemberRepository;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("admin");
    }
    @GetMapping("/manage")
    public ResponseEntity<String> manage() {
        return ResponseEntity.ok("manage");
    }
    @GetMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("sslogin");
    }
    @PostMapping("/join")
    public ResponseEntity<?> join(Member member) {
        System.out.println(member);
        String rawPassword = member.getPassword();
        String encPwd = bCryptPasswordEncoder.encode(rawPassword);
        member.setPassword(encPwd);
        memberRepository.save(member);

//        return ResponseEntity.ok("check");
        return new ResponseEntity<Member>(member,HttpStatus.OK);
    }

    @PostMapping("/logingo")
    public ResponseEntity<String> login(Member member) {
//        Member principal = memberRepository.findByEmail(member.getEmail());
//
//        if(principal == null){
//            throw new UsernameNotFoundException("해당 사용자를 찾을수 없습니다.:" + member.getEmail());
//        }
        return ResponseEntity.ok("check5");
    }

    @GetMapping ("/join")
    public ResponseEntity<String> join1() {
//        System.out.println("test");
        
        return ResponseEntity.ok("check2");
    }

    @GetMapping("/joinProc")
    public ResponseEntity<String> joinProc() {
        return ResponseEntity.ok("joinProc");
    }

}
