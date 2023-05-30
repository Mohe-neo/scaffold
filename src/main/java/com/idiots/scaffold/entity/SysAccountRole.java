package com.idiots.scaffold.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author idiots-devil
 * @since 2023-05-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAccountRole implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long accountId;

    private Long roleId;
}
