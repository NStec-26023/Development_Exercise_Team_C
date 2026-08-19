package com.example.fullness.stationary.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 社員ログイン画面の入力内容を保持するフォームクラスです。
 * ユーザーから送信されるアカウント名とパスワードを受け取り、バリデーションを実行します。
 */
@Data
public class EmployeeLoginForm {
    /** アカウント名 */
    @NotBlank(message = "アカウント名を入力してください")
    private String username;
    /** パスワード */
    @NotBlank(message = "パスワードを入力してください")
    private String password;
}
