package com.wyp.common.entity;

import com.wyp.common.annotation.CheckPassword;
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
@CheckPassword
public class User implements Serializable {

    private Long id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
    @Email
    private String email;

    private String phone;
    @Min(3)
    private int age;

    private interface Add {
    }

    private interface Delete {
    }

    private interface Update {
    }

    private interface Query {
    }
}
