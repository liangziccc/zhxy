package com.etoak.zhxy.utils;



import java.text.SimpleDateFormat;
import java.util.Date;

public class ETDateUtils {

    public static Date String2Date(String birth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(birth);
        }catch (Exception e){
            e.printStackTrace(); return null;
        }

    }

    public static Date timestampStr2Date(String s) {
        try {
            Date date = new Date();
            date.setTime(Long.parseLong(s));
            return  date;
        } catch (Exception e) {
            e.printStackTrace();return  null;
        }
    }
}
