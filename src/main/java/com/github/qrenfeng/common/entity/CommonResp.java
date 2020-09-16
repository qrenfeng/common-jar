package com.github.qrenfeng.common.entity;

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
public class CommonResp<T> {

    private Boolean state;

    private T data;

    private CommonError error;

    public CommonResp() {
    }

    public CommonResp(T data) {
        this.state = true;
        this.data = data;
    }

    public CommonResp(CommonError error){
        this.state = false;
        this.error = error;
    }
}
