package com.github.qrenfeng.common.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>通用分页返回</p>
 * <p>Created by qrf on 2018/10/26.</p>
 *
 * @author qrf
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonPageResp<T> {

    private Long index;

    private Long size;

    private Long total;

    private List<T> rows;
}
