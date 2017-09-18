package com.wyp.common.validator;

import com.wyp.common.annotation.CheckPassword;
import com.wyp.common.entity.User;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
/**
 * @Author 王亚平【yapingw@dingtalk.com】
 * @Date 2017/9/18 15:29
 * @description 密码一致性校验
 */
public class CheckPasswordValidator implements ConstraintValidator<CheckPassword, User> {
    @Override
    public void initialize(CheckPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if (Objects.isNull(user)) {
            return true;
        }
        if (!StringUtils.hasText(user.getPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{password.null}")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
            return false;
        }
        if (!StringUtils.hasText(user.getConfirmPassword())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{confirmPassword.null}")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
            return false;
        }


        if (!user.getPassword().trim().equals(user.getConfirmPassword().trim())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{password.confirmPassword.error}")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
            return false;
        }




        return true;
    }
}