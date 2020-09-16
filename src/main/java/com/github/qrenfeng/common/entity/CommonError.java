package com.github.qrenfeng.common.entity;

import com.github.qrenfeng.common.exception.CommonException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * <p></p>
 * @author qrf
 * @since 2020/2/17
 */
@Data
@Builder
@AllArgsConstructor
public class CommonError {

    private String code;

    private String msg;

    public CommonError(){

    }

    public CommonError(CommonException ex) {
        this.code = ex.getCode();
        this.msg = ex.getMsg();
    }
}
