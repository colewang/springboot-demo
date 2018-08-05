package com.example.demo.Domain.Validator;

import com.example.demo.Domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        User user = (User)target;
        checkAge(errors, user);
        checkName(errors, user);
    }

    private void checkName(Errors errors, User user) {
        if (user.getName().isEmpty()) {
            errors.rejectValue("name", "empty.value");
        }
    }

    private void checkAge(Errors errors, User user) {
        if (user.getAge() < 0) {
            errors.rejectValue("age", "negative.value");
        } else if (user.getAge() > 150) {
            errors.rejectValue("age", "too.darn.old");
        }
    }
}
