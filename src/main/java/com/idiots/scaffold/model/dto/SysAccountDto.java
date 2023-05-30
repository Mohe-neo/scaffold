package com.idiots.scaffold.model.dto;

import lombok.Data;

/**
 * @author idiots-devil
 * @since 2023-05-29
 */
@Data
public class SysAccountDto {
    /**
     * 用户名
     */
    private String username;
    /**
     * 备注
     */
    private String remark;
    private Long roleId;
    private String roleName;
}
