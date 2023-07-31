package ssafy.project.jobterview.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssafy.project.jobterview.dto.LoginFormDto;
import ssafy.project.jobterview.domain.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {



    @Autowired
    private AuthenticationManager authenticationManager;



    //예시 코두 입니다
    @GetMapping("/ok")
    public ResponseEntity<?> ok(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String useremail = authentication.getName(); //요러면 이제 유저의 이메일을 가져 옵니다
        System.out.println(useremail);
        return new ResponseEntity<String>(useremail, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(Member member) {

        return new ResponseEntity<Member>(member, HttpStatus.OK);
    }

    @PostMapping ("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {

        return ResponseEntity.ok("1");
    }
}
