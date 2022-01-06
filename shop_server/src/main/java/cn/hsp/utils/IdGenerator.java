package cn.hsp.utils;

import java.util.UUID;

public class IdGenerator {
    public static String generateId() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
