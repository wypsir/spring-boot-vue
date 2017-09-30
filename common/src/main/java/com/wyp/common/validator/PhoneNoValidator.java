package com.wyp.common.validator;

import com.wyp.common.annotation.PhoneNo;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNoValidator implements ConstraintValidator<PhoneNo,String> {

    @Override
    public void initialize(PhoneNo constraintAnnotation) {
    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(StringUtils.isEmpty(value))
            return true;
        if(value.matches("((\\+86)|(86))?1[3|4|5|8]\\d{9}")){
            return true;
        }
        return false;
    }
}