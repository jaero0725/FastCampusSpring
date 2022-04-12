package kr.ac.zeroco.hs_springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity  //얘가 @Configuration이 들어있음
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //AuthenticationProvider가 PasswordEncoder와 UserDetailsService를 사용한다.

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // BCryptPasswordEncoder 생성
    }

    //인증과 관련된 메서드,
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        //어떤 UserDetailsService를 사용하고, 어떤 PasswordEncoder를 사용하는지
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
