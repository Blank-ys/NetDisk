package com.dbank.util;

import java.util.UUID;

/**
 * UUID生成器
 */
public class UUIDGenerator {

    private UUIDGenerator(){}

    /**
     * 生成UUID
     * @return
     */
    public static String generate(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
