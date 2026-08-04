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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 1. アクセス権限（認可）の設定
                .authorizeHttpRequests(auth -> auth
                        // 【例1】特定のページや管理画面だけ認証を必須にする
                        .requestMatchers("/purchase/confirm").authenticated()

                        // 【例2】上記以外のすべてのリクエストはデフォルトでアクセスを許可する
                        .anyRequest().permitAll())

                // 2. ログイン設定
                .formLogin(form -> form
                        .loginPage("/login") // 自作のログイン画面のURL
                        .loginProcessingUrl("/login") // 認証処理のPOST先
                        .defaultSuccessUrl("/", true) // ログイン成功後は常にトップ画面("/")へリダイレクト
                        .permitAll())

                // 3. ログアウト設定
                .logout(logout -> logout
                        .logoutUrl("/logout") // ログアウトを実行するURL
                        .logoutSuccessUrl("/") // ログアウト成功後はトップ画面("/")へリダイレクト
                        .permitAll());
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
