package com.idiots.scaffold.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**

 * @author idiots-devil
 * @since 2023-05-28
 */
@Data
public class SysLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long accountId;

    private LocalDateTime loginTime;

    private String host;

    private String address;

    private String userAgent;

    private String status;

    private String message;
}
