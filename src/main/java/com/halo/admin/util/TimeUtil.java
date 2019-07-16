package com.halo.admin.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Auther: halo
 * @Date: 2019/7/14 13:24
 * @Description:
 */
public class TimeUtil {
    private static final DateTimeFormatter FORMATTER_YMDT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * 格式化时间
     * @param localDateTime
     * @return
     */
    public static String formatToYMDT(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATTER_YMDT);
    }
}
