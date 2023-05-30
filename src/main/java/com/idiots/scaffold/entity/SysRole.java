package com.idiots.scaffold.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**

 * @author idiots-devil
 * @since 2023-05-28
 */
@Data
public class SysRole implements Serializable {

    
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String code;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    private Integer status;

    private Integer sort;
}
