package ssafy.project.jobterview.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터 등록
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .authorizeRequests()
                .antMatchers("/api/hello").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/join").permitAll()
                .antMatchers(HttpMethod.POST, "/api/join").permitAll()
                .antMatchers("/api/admin/**").access("hasRole('Role_Admin')")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/api/login");

    }
}
