package com.example.fullness.stationary.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueAccountNameValidator.class)
@Target({ ElementType.FIELD }) // フィールドに対して付与できるようにする
@Retention(RetentionPolicy.RUNTIME) // 実行時まで保持
public @interface UniqueAccountName {
    // エラー時のデフォルトメッセージ
    String message() default "このアカウント名は既に使用されています";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}