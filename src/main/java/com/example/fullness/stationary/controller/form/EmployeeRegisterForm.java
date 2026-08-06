package com.example.fullness.stationary.controller.form;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

//担当者アカウント登録用Form
@Data
public class EmployeeRegisterForm implements Serializable {
    @NotNull(message = "社員を選択してください。")
    private String empName; // 選択された社員の名前

    @NotBlank(message = "アカウント名を入力してください。")
    @Size(min = 5, max = 20, message = "アカウント名は5文字以上20文字以内で入力してください。")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "アカウント名は半角英数字で入力してください。")
    // ToDo:重複値確認バリデーション追加
    private String accountName;

    @NotBlank(message = "パスワードを入力してください。")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "パスワードは半角英数字で入力してください。")
    @Size(min = 5, max = 20, message = "パスワードは5文字以上20文字以内で入力してください。")
    private String password;
}
