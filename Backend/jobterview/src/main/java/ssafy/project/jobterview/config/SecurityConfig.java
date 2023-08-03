package ssafy.project.jobterview.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import ssafy.project.jobterview.config.auth.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터 등록
//@EnableOAuth2Clien
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    private PrincipalDetailService principalDetailService;
    private PrincipalOauth2UserService principalOauth2UserService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;;

    @Autowired
    public SecurityConfig( PrincipalDetailService principalDetailService,
                           @Lazy PrincipalOauth2UserService principalOauth2UserService,
                           @Lazy BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.principalDetailService = principalDetailService;
        this.principalOauth2UserService = principalOauth2UserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    private SocialAuthenticationSuccessHandler socialAuthenticationSuccessHandler;



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost:8081"); // 허용할 Origin 설정, *은 모든 Origin 허용
//        configuration.addAllowedMethod("*"); // 모든 HTTP Method 허용
//        configuration.addAllowedHeader("*"); // 모든 HTTP Header 허용
//        configuration.setAllowCredentials(true); // 'Access-Control-Allow-Credentials'를 true로 설정
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    // CacheControlFilter 빈 등록
    @Bean
    public CacheControlFilter cacheControlFilter() {
        return new CacheControlFilter();
    }




    // 시큐리티가 대신 로그인해주는데 password를 가로채는데
    // 해당 password가 뭘로 해쉬화해서 회원가입이 되었는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교가능
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePwd());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
//                .antMatchers(HttpMethod.OPTIONS, "/**") // 이게 뭐임?
                .antMatchers(
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/h2-console/**"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//      http.exceptionHandling()
//              .accessDeniedHandler(jwtAccessDeniedHandler);
        http
                .headers()
                .cacheControl().disable(); // 캐싱 금지 설정 추가
        http.cors();
        http
                .addFilterBefore(cacheControlFilter(), SecurityContextHolderAwareRequestFilter.class)
                .csrf().disable()
                .authorizeRequests()

                    .antMatchers("/oauth2/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/oauth2/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/member/join").permitAll()
                    .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                    .antMatchers("/auth/loginpage").permitAll()
                    .antMatchers(HttpMethod.GET,"/member/nicknameCheck").permitAll()
                    .antMatchers(HttpMethod.GET,"/member/emailCheck").permitAll()
                    .antMatchers("/admin/**").access("hasRole('ROLE_admin')")
                    .anyRequest().authenticated()

                .and()
                .formLogin()
                    .loginPage("/auth/loginpage")
                    //.loginPage("http://localhost:8081/auth/login")
                    .usernameParameter("email")
                    .loginProcessingUrl("/auth/login")
                    .successHandler(customAuthenticationSuccessHandler)
                    .failureHandler(customAuthenticationFailureHandler)
                .and()
                .logout()
                    .logoutUrl("/auth/logout")
                    .addLogoutHandler((request, response, authentication) -> {
                        // 사실 굳이 내가 세션 무효화하지 않아도 됨.
                        // LogoutFilter가 내부적으로 해줌.
                        HttpSession session = request.getSession();
                        if (session != null) {
                            session.invalidate();
                        }
                    })
                    .logoutSuccessHandler(customLogoutSuccessHandler)// 로그아웃 URL 설정
                    .clearAuthentication(true)// 현재 인증 정보 삭제
                    .invalidateHttpSession(true) // HTTP 세션 무효화
                    .deleteCookies("JSESSIONID","remember-me") // 로그아웃 시 쿠키 삭제
                .and()
                .oauth2Login()
                    .userInfoEndpoint()
                    .userService(principalOauth2UserService)
                    .and()
                    .successHandler(socialAuthenticationSuccessHandler);


        //중복 로그인
        http.sessionManagement()
                .maximumSessions(1) //세션 최대 허용 수
                .maxSessionsPreventsLogin(false); // false이면 중복 로그인하면 이전 로그인이 풀린다.

    }

}





