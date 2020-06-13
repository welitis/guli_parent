package com.welisit.eduservice.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author welisit
 * @create 2020-06-13 22:23
 */
@Data
@ApiModel(description = "查询讲师的请求参数对象")
public class TeacherQueryParam {

    @ApiModelProperty("教师名称,模糊查询")
    private String name;

    @ApiModelProperty("头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2019-01-01 10:10:10")
    private String end;
}
