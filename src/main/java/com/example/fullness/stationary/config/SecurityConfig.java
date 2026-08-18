package com.example.fullness.stationary.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.fullness.stationary.entity.EmployeeAccount;
import com.example.fullness.stationary.repository.CustomerRepository;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;
import com.example.fullness.stationary.service.CustomAuthenticationFailureHandler;
import com.example.fullness.stationary.service.CustomAuthenticationSuccessHandler;
// import com.example.fullness.stationary.service.CustomerDetailsService;
import com.example.fullness.stationary.service.EmployeeDetailsService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
        private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

        public SecurityConfig(CustomAuthenticationFailureHandler customAuthenticationFailureHandler,
                        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
                this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
                this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        }

        // @Autowired
        // EmployeeDetailsService employeeDetailsService;
        // @Autowired
        // CustomerDetailsService customerDetailsService;

        // 担当者ログイン・ログアウト
        @Bean
        @Order(1)
        public SecurityFilterChain employeeSecurityFilterChain(HttpSecurity http) throws Exception {
                // DaoAuthenticationProvider employeeProvider = new DaoAuthenticationProvider();
                // employeeProvider.setUserDetailsService(employeeDetailsService);
                // employeeProvider.setPasswordEncoder(passwordEncoder());
                http
                                .securityMatcher("/admin/**") // /admin/ 以下のリクエストにのみ適用
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/admin/login", "/admin").permitAll() // ログイン画面などは認証不要
                                                .requestMatchers("/admin/logout").authenticated() // ログインしたユーザーのみ
                                                .requestMatchers("/admin/**").hasRole("EMPLOYEE") // EMPLOYEEのみ
                                )
                                .formLogin(form -> form
                                                .loginPage("/admin/login") // 担当者アカウントログイン画面
                                                .loginProcessingUrl("/admin/login-process") // フォームの送信先
                                                .usernameParameter("username")
                                                .passwordParameter("password")
                                                .successHandler(customAuthenticationSuccessHandler)
                                                .failureHandler(customAuthenticationFailureHandler)
                                                .defaultSuccessUrl("/admin", true) // ログイン成功後の遷移先
                                                // .failureUrl("/admin/login") //ハンドラーに処理を任せる
                                                .permitAll())
                                // ログアウト制御
                                .logout(logout -> logout
                                                .logoutUrl("/admin/logout") // ログアウトを実行するURL
                                                .logoutSuccessUrl("/admin") // ログアウト成功後の遷移先
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID")
                                                .clearAuthentication(true));

                return http.build();
        }

        // 顧客ログイン、
        // ログアウト
        @Bean
        @Order(2)
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                // 1. アクセス権限（認可）の設定
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/").permitAll()
                                                // 【例1】特定のページや管理画面だけ認証を必須にする
                                                .requestMatchers("/purchase/confirm").authenticated()
                                                // 【例2】上記以外のすべてのリクエストはデフォルトでアクセスを許可する
                                                .anyRequest().permitAll())

                                // 2. ログイン設定
                                .formLogin(form -> form
                                                .loginPage("/login") // ログイン画面のURL
                                                .loginProcessingUrl("/login") // フォームの処理先
                                                .usernameParameter("mailAddress")
                                                .passwordParameter("password")
                                                .defaultSuccessUrl("/", true) // ログイン成功後は常にトップ画面("/")へリダイレクト
                                                .failureUrl("/login")
                                                .permitAll())

                                // 3. ログアウト設定
                                .logout(logout -> logout
                                                .logoutUrl("/logout") // ログアウトを実行するURL
                                                .logoutSuccessUrl("/") // ログアウト成功後はトップ画面("/")へリダイレクト
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID")
                                                .clearAuthentication(true));
                return http.build();
        }

        // パスワードをハッシュ化するためのエンコーダー（BCrypt）
        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
