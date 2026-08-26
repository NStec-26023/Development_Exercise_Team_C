package com.example.fullness.stationary.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.fullness.stationary.repository.EmployeeAccountRepository;

@Component
public class UniqueAccountNameValidator implements ConstraintValidator<UniqueAccountName, String> {
    @Autowired
    private EmployeeAccountRepository employeeAccountRepository;

    @Override
    public void initialize(UniqueAccountName constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // null または空の場合はスキップ（他のバリデーターに任せる）
        if (value == null || value.isEmpty()) {
            return true;
        }

        return employeeAccountRepository.findByName(value) == null;
    }
}
