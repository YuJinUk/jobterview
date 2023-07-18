package ssafy.project.jobterview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.project.jobterview.domain.Member;
import ssafy.project.jobterview.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private UserRepository userRepository;

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
    public ResponseEntity<String> join(Member member) {
        System.out.println(member);
        System.out.println("null");

        return ResponseEntity.ok("check");
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
