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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import ssafy.project.jobterview.config.auth.*;

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



    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // 허용할 Origin 추가
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
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

        http.cors();
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/member/emailConfirm").permitAll() // 이메일 전송 URL 허용
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers(HttpMethod.POST,"/oauth2/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/oauth2/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/oauth2/**").permitAll()
                //.antMatchers(HttpMethod.POST,"/auth/logout").permitAll()
                .antMatchers("/auth/ok").permitAll()
                .antMatchers(HttpMethod.POST, "/member/join").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers("/admin/**").access("hasRole('admin')")
                //  .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .usernameParameter("email")
                .loginProcessingUrl("/auth/login")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .and()
                .logout()
                .logoutUrl("/auth/logout") // 로그아웃 URL 설정
                .logoutSuccessHandler(customLogoutSuccessHandler) // 로그아웃 성공 후 리다이렉트할 URL 설정
                .invalidateHttpSession(true) // HTTP 세션 무효화
                .deleteCookies("JSESSIONID") // 로그아웃 시 쿠키 삭제
                .clearAuthentication(true);// 현재 인증 정보 삭제
                /*.and()
                        .oauth2Login()
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureUrl("/auth/ok")		// 로그인 실패 시 /loginForm으로 이동
                        .userInfoEndpoint()		// 로그인 성공 후 사용자정보를 가져온다
                        .userService(principalOauth2UserService);	//사용자정보를 처리할 때 사용한다
*/
        //http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        //중복 로그인
        http.sessionManagement()
                .maximumSessions(1) //세션 최대 허용 수
                .maxSessionsPreventsLogin(false); // false이면 중복 로그인하면 이전 로그인이 풀린다.

    }


}
