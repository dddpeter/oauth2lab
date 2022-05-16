package io.spring2go.authcodeserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

/**
 * spring security 配置类
 */

/**
 * @author hqins
 * @author hqins
 * @create 2022/5/14 14:54
 **/
@Configuration
@EnableWebSecurity
/** 开启security注解 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    PasswordEncoder passwordEncoder;
    /**
     * 自定义UserDetailsService用来从数据库中根据用户名查询用户信息以及角色信息
     */
    @Resource
    public UserDetailsService jdbcUserDetailsService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jdbcUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }
    @Override
    public void  configure(WebSecurity web){
        web.ignoring().antMatchers(
                "/images/**",
                "/css/**",
                "/webjars/**");
    }

    /**
     * 验证配置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login","/logout.do","/user/logout").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login.do")
                .usernameParameter("username")
                .passwordParameter("password")
                //.loginPage("/login")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout.do"))
                .and()
                .userDetailsService(jdbcUserDetailsService)
                .csrf().disable();
    }

}


