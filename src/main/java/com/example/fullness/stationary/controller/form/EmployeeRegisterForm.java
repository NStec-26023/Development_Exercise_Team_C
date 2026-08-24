package com.example.fullness.stationary.controller.form;

import com.example.fullness.stationary.validator.UniqueAccountName;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeRegisterForm {
    private Integer accId; // 自動採番アカウントID

    @NotNull(message = "社員名を選択してください")
    private Integer empId; // 選択された社員の社員ID

    private String empName; // 選択された社員名

    @NotBlank(message = "アカウントを入力してください")
    @Size(min = 5, max = 20, message = "アカウント名は5～20文字で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "アカウント名は半角英数字で入力してください")
    @UniqueAccountName(message = "このアカウント名は既に使用されています")
    private String accountName; // 入力されたアカウント名

    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 5, max = 20, message = "パスワードは5～20文字で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "パスワードは半角英数字で入力してください")
    private String password; // 入力されたパスワード
}
