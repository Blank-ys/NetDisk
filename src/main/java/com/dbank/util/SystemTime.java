package com.dbank.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemTime {

    /**
     * 获得当前系统时间
     * @return
     */
    public static String getTime(){
        SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return tempDate.format(new Date(System.currentTimeMillis()));
    }
}
