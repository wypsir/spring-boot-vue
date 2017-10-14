package com.yaping.common.entity;

import com.yaping.common.annotation.CheckPassword;
import com.yaping.common.annotation.PhoneNo;
import com.yaping.common.valid.Valid;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/18 13:47
 * @description
 */
@Data
@CheckPassword(groups = {Valid.Register.class})
public class User implements Serializable {

    private Long id;
    @NotBlank(groups = {Valid.Login.class, Valid.Register.class})
    private String username;
    @NotBlank(groups = {Valid.Login.class, Valid.Register.class})
    private String password;
    @NotBlank(groups = {Valid.Register.class})
    private String confirmPassword;
    @Email(groups = {Valid.Register.class})
    private String email;

    @NotBlank(groups = {Valid.Register.class})
    @PhoneNo(groups = {Valid.Register.class})
    private String phone;
    @Min(3)
    private int age;

}
