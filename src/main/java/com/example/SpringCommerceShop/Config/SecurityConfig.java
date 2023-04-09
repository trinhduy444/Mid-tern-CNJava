package com.example.SpringCommerceShop.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig  {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/", "/cart", "/static/**").permitAll() // cho phép truy cập vào các trang không yêu cầu đăng nhập
//                .anyRequest().authenticated() // yêu cầu đăng nhập cho các trang còn lại
//                .and()
//            .formLogin()
//                .loginPage("/login") // sử dụng trang login của riêng bạn
//                .permitAll() // cho phép truy cập vào trang login mà không yêu cầu đăng nhập
//                .and()
//            .logout()
//                .permitAll(); // cho phép truy cập vào trang logout mà không yêu cầu đăng nhập
//    }
}
