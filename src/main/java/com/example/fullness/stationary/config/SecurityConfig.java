package com.example.fullness.stationary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        // 担当者ログイン・ログアウト
        @Bean
        public SecurityFilterChain employeeSecurityFilterChain(HttpSecurity http) throws Exception {

                http
                                .securityMatcher("/admin/**") // /admin/ 以下のリクエストにのみ適用
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/admin/login").permitAll() // ログイン画面などは認証不要
                                                .anyRequest().hasRole("emp") // その他はEMPLOYEEロールが必要
                                )
                                .formLogin(form -> form
                                                .loginPage("/admin/login") // 従業員用カスタムログイン画面
                                                .loginProcessingUrl("/admin/login-process") // フォームの送信先
                                                .defaultSuccessUrl("/admin", true) // ログイン成功後の遷移先
                                                .failureUrl("/admin/login")
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
                                                .loginPage("/login") // 自作のログイン画面のURL
                                                .loginProcessingUrl("/login") // 認証処理のPOST先
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

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
                return configuration.getAuthenticationManager();
        }
}
