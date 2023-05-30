package com.idiots.scaffold.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author idiots-devil
 * @since 2023-05-29
 */
@Data
public class LInfo {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空!")
    private String username;
    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空!")
    private String password;
}
