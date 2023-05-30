package com.idiots.scaffold.lang;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author idiots-devil
 * @since 2023-05-28
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public static Result success(Object data){
        return new Result(0,"success",data);
    }

    public static Result success(){
        return new Result(0,"success",null);
    }

    public static Result failure(String message){
        return new Result(-1,message,null);
    }

    public static Result failure(RespCode resp) {
        return new Result(resp.getCode(), resp.getMessage(), null);
    }
}
