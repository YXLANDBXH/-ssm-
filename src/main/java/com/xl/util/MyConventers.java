package com.xl.util;

import org.aspectj.weaver.ast.Var;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author XLong
 * @create 2021-07-20 15:29
 * springmvc 转化器  String--》date
 */
public class MyConventers implements Converter<String,Date>{

    @Override
    public Date convert(String s) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss").parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
