package com.ezen.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

      @Autowired
      private DataSource dataSource;
      
      @Bean
      public SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
         
         security.authorizeRequests().antMatchers("/").permitAll();
         security.authorizeRequests().antMatchers("/system/**").permitAll();
         security.authorizeRequests().antMatchers("/board/**").authenticated();
         security.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
         
         security.csrf().disable();
         
         security.formLogin().loginPage("/system/login").defaultSuccessUrl("/board/getBoardList", true);
         
         security.exceptionHandling().accessDeniedPage("/system/accessDenied.html");
         
         security.logout().invalidateHttpSession(true).logoutSuccessUrl("/");
         
         return security.build();
         
      }
   
      
      @Autowired
      public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
         
         String query1 = "SELECT id username, concat('{noop}', password) password, true enabled "
               + "FROM member WHERE id=?";
         
         String query2 = "SELECT id, role FROM member WHERE id=?";
         
         auth.jdbcAuthentication().dataSource(dataSource)
            .usersByUsernameQuery(query1)
            .authoritiesByUsernameQuery(query2);
         
      }

}













