package com.welisit.servicebase.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author welisit
 * @Description 自定义api异常
 * @create 2020-06-14 10:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "自定义api异常")
public class ApiException extends RuntimeException {

    @ApiModelProperty("状态码")
    private Integer code;

    private String message;

}
