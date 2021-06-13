package org.bluron.postview.common.config;

import org.bluron.postview.common.entity.vo.Message;
import org.bluron.postview.common.util.JwtTokenBuilder;
import org.bluron.postview.entity.pojo.SysUser;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;

/**
 * spring security配置
 *
 * @author JuLei
 * @since 1.0.0_2020年04月06日
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JwtTokenBuilder jwtTokenBuilder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/login", "/api/**").permitAll()
                .anyRequest().authenticated().and()
                .logout()
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    Message message = new Message(true, "登出成功");
                    PrintWriter writer = httpServletResponse.getWriter();
                    writer.write(message.toString());
                }).and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    SysUser sysUser = (SysUser) authentication.getPrincipal();
                    String token = jwtTokenBuilder.createToken(sysUser);
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    PrintWriter writer = httpServletResponse.getWriter();
                    Message message = new Message();
                    if ("".equals(token)) {
                        message.setResult(false);
                        message.setMsg("登录失败");
                    } else {
                        message.setResult(true);
                        message.setMsg("登录成功");
                        message.setParam(token);
                    }
                    writer.write(message.toString());
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter writer = httpServletResponse.getWriter();
                    Message message = new Message();
                    message.setResult(false);
                    message.setMsg("登录失败");
                    writer.write(message.toString());
                }).and()
                .addFilterBefore(jwtAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                // 拒绝访问异常处理
                .accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter writer = httpServletResponse.getWriter();
                    Message message = new Message();
                    message.setResult(false);
                    message.setMsg("认证失败");
                    writer.write(message.toString());
                })
                // 认证异常处理
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, e) -> {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter writer = httpServletResponse.getWriter();
                    Message message = new Message();
                    message.setResult(false);
                    message.setMsg("认证失败");
                    writer.write(message.toString());
                });
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        return bCryptPasswordEncoder;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Collections.singletonList("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    JwtAuthenticationFilter jwtAuthenticationFilterBean() {
        return new JwtAuthenticationFilter();
    }

}
