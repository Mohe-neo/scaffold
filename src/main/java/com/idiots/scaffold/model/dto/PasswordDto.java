package com.idiots.scaffold.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author idiots-devil
 * @since 2023-05-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDto {
    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;
    /**
     * 确认密码
     */
    private String confirmPassword;
}
