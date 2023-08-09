package ssafy.project.jobterview.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import ssafy.project.jobterview.config.auth.*;

import javax.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터 등록
//@EnableOAuth2Clien
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalDetailService principalDetailService;
    private final PrincipalOauth2UserService principalOauth2UserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;
    @Autowired
    private SocialAuthenticationSuccessHandler socialAuthenticationSuccessHandler;
    @Autowired
    private RequestLoggingFilter requestLoggingFilter;
    @Autowired
    public SecurityConfig(PrincipalDetailService principalDetailService,
                          @Lazy PrincipalOauth2UserService principalOauth2UserService,
                          @Lazy BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.principalDetailService = principalDetailService;
        this.principalOauth2UserService = principalOauth2UserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

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
                .antMatchers(
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/h2-console/**"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .cacheControl().disable(); // 캐싱 금지 설정 추가
        http.cors();
        http
                .addFilterBefore(cacheControlFilter(), SecurityContextHolderAwareRequestFilter.class)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers(HttpMethod.POST, "/oauth2/**").permitAll()
                .antMatchers(HttpMethod.POST, "/member/join").permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                .antMatchers("/health_check").permitAll()
                .antMatchers("/admin/members/cnt").permitAll()
                .antMatchers(HttpMethod.GET, "/member/nicknameCheck").permitAll()
                .antMatchers(HttpMethod.GET, "/member/emailCheck").permitAll()
                .antMatchers(HttpMethod.POST, "/member/emailconfirm").permitAll()
                .antMatchers(HttpMethod.POST,"/member/passwordEmailConfirm").permitAll()
                .antMatchers(HttpMethod.PUT,"/member/resetPassword").permitAll()
                .antMatchers(HttpMethod.PUT,"/member").permitAll()
                .antMatchers(HttpMethod.PUT,"/member/withdraw").permitAll()
                .antMatchers("/member/resetPassword/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/member/emailauth/**").permitAll()
                .antMatchers("/emailauth/**").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_admin')")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
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
                .deleteCookies("JSESSIONID", "remember-me") // 로그아웃 시 쿠키 삭제
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
    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> loggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(requestLoggingFilter);
        // 필터 순서를 원하는대로 조정할 수 있습니다.
        registrationBean.setOrder(1);
        return registrationBean;
    }

}