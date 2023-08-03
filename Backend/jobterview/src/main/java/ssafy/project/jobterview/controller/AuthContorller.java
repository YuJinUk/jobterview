package ssafy.project.jobterview.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AuthContorller {

    @GetMapping("/auth/loginpage")
    public String showLoginPage(HttpServletResponse response) throws IOException {
        System.out.println("열로 오나여?");
        // 사용자가 로그인 폼으로 접근하면 로그인 폼 페이지로 리다이렉트
        //response.sendRedirect("http://localhost:8081/auth/login");
        return "go to login";

    }


}
