package com.example.fullness.stationary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.fullness.stationary.handler.CustomAuthenticationFailureHandler;
import com.example.fullness.stationary.handler.CustomAuthenticationSuccessHandler;

/**
 * Spring Securityを用いたWebアプリケーションのセキュリティ設定クラスです。
 * 担当者向けのセキュリティフィルターチェーンや、パスワードエンコーダーの設定を行います。
 *
 * @author 陳以勒
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
        /** 認証失敗時のカスタムハンドラ */
        private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
        /** 認証成功時のカスタムハンドラ */
        private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

        /**
         * コンストラクタインジェクションにより、認証成功・失敗のカスタムハンドラを設定します。
         *
         * @param customAuthenticationFailureHandler 認証失敗ハンドラ
         * @param customAuthenticationSuccessHandler 認証成功ハンドラ
         */
        public SecurityConfig(CustomAuthenticationFailureHandler customAuthenticationFailureHandler,
                        CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
                this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
                this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
        }

        /**
         * 担当者（管理者）向けのセキュリティフィルターチェーンを設定します。
         * 対象URLやログイン・ログアウトの挙動を定義します。
         *
         * @param http HttpSecurityオブジェクト
         * @return 設定済みのSecurityFilterChain
         * @throws Exception 設定時に発生する例外
         */
        @Bean
        @Order(1)
        public SecurityFilterChain employeeSecurityFilterChain(HttpSecurity http)
                        throws Exception {
                // DaoAuthenticationProvider employeeProvider = new
                // DaoAuthenticationProvider();
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

        /**
         * パスワードをハッシュ化するためのBCryptエンコーダーのBeanを定義します。
         *
         * @return BCryptPasswordEncoderインスタンス
         */
        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
