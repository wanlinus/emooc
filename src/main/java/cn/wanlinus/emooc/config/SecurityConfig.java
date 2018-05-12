/*
 * Copyright (C) 2018 - wanli <wanlinus@qq.com>
 *
 * This file is part of emooc
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package cn.wanlinus.emooc.config;

import cn.wanlinus.emooc.service.impl.UserDetailsServiceImpl;
import cn.wanlinus.emooc.service.impl.PasswordEncoderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author wanli
 * @date 2018-02-22 11:05
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    UserDetailsService detailsService() {
        return new UserDetailsServiceImpl();
    }

    /**
     * 定义密码加密Bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoderImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("123456").roles("ROLE_USER").and()
//                .withUser("admin").password("123456").roles("ADMIN");
        //使用UserDetails实现用户认证
        auth.userDetailsService(detailsService()).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dispatcher")
                .failureUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
//                .rememberMe()
//                .and()
                .authorizeRequests()
                .antMatchers("/*/**").permitAll()
                .antMatchers("/check/*/**").permitAll()
                .antMatchers("/active/*/**").permitAll()
                .antMatchers("/user/*/**").hasAuthority("USER")
                .antMatchers("/course/*/**").hasAuthority("USER")
                .antMatchers("/teacher/*/**").hasAuthority("TEACHER")
                .antMatchers("/admin/*/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().ignoringAntMatchers("/*");
    }
}
