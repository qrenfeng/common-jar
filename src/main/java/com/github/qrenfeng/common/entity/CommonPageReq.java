package com.github.qrenfeng.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>通用分页请求</p>
 * <p>Created by qrf on 2019/4/28.</p>
 * @author qrf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonPageReq<T> {

    @NotNull(message = "页数不为空")
    @Min(value = 1, message = "页数最小为1")
    @Max(value = Long.MAX_VALUE, message = "页数最大为长整数")
    private Long index;

    @NotNull(message = "分页大小不为空")
    @Min(value = 1, message = "分页大小最小为1")
    @Max(value = Long.MAX_VALUE, message = "分页大小最大为长整数")
    private Long size;

    @Valid
    private T query;
}
