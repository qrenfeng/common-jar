package com.github.qrenfeng;

import com.github.qrenfeng.common.utils.HttpUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException {
        System.out.println(HttpUtils.get("http://www.baidu.com"));
    }
}
