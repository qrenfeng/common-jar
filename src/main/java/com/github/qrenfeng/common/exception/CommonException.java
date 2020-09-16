package com.github.qrenfeng.common.exception;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * <p></p>
 * @author qrf
 * @since 2020/2/17
 */
@Data
@Builder
public class CommonException extends Exception implements Serializable {
    /**
     * 异常代码
     */
    private String code;

    /**
     * 异常信息
     */
    private String msg;

    public CommonException() {
        super();
    }

    public CommonException(String code, String msg) {
        super(code+":"+msg);
        this.code = code;
        this.msg = msg;
    }
}
