package com.github.qrenfeng.common.utils;



import com.github.qrenfeng.common.utils.seq.Sequence;
import com.github.qrenfeng.common.utils.seq.StringPool;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ID生成工具
 * @author qrf
 * @since 2020/2/17
 */
public class IDUtils {

    private static Sequence WORKER = new Sequence();

    /**
     * 生成唯一18位数字ID
     * @return 18位数字ID
     */
    public static String newId(){
        return String.valueOf(WORKER.nextId());
    }

    /**
     * 生成唯一18位数字ID
     * @return
     */
    public static Long newLongId(){
        return WORKER.nextId();
    }
    /**
     * 生成GUID，32位
     * @return 32位GUID
     */
    public static String newGuid() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextLong(), random.nextLong()).toString().replace(StringPool.DASH, StringPool.EMPTY);
    }

}


