package com.idiots.scaffold.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

/**

 * @author idiots-devil
 * @since 2023-05-28
 */
@Data
public class SysAccount implements Serializable, UserDetails {


    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String icon;

    private String email;

    private Integer type;

    private String realName;

    private String remark;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private LocalDateTime loginTime;

    private Integer enabled;

    private Integer locked;
    @TableField(exist = false)
    private Long roleId;

    @TableField(exist = false)
    private String roleName;

    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities == null ? null : authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked==1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled == 1;
    }
}
