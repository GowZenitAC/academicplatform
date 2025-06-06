package com.armando.academicplatform.validations;

import com.armando.academicplatform.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class IsExistsDbValidation implements ConstraintValidator<IsExistDb, String> {
    @Autowired
    private UserService userService;
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (userService == null)
            return true;
        return !userService.existsByUsername(s);
    }
}
