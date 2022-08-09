package com.zmz.testreg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式测试
 */
public class RegExrMain {


    public static void main(String[] args) {
        String s = "http://www.baidu.com/MESHOP/01/02/dd/MESHOPfile12345678h.xls";
        String str = "";
        Pattern pattern = Pattern.compile("(?<=\\.)\\w*$");
        Matcher matcher = pattern.matcher(s);
        System.err.println(matcher.matches());
    }


}
