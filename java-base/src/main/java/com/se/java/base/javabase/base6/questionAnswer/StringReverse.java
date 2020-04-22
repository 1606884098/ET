package com.se.java.base.javabase.base6.questionAnswer;

/**
 * @Author Science
 * @Date 2020/4/19 20:15
 * @Version 1.0
 */
public class StringReverse {
    public static void main(String[] args) {
        String s = "abc123";

        System.out.println("----------------");
        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.print(s.charAt(i));
        }
        System.out.println("----------------");

        System.out.println("变换前: " + s);
        System.out.println("变换后: " + reverse1(s));
        System.out.println("变换后: " + reverse2(s));
        System.out.println("变换后: " + reverse3(s));
    }

    //1.利用 StringBuffer（线程安全） 或 StringBuilder 的 reverse 成员方法:
    public static String reverse1(String str) {
        return new StringBuilder(str).reverse().toString();
    }
    //2.利用 String 的 toCharArray 方法先将字符串转化为 char 类型数组，然后将各个字符进行重新拼接:
    public static String reverse2(String str) {
        char[] chars = str.toCharArray();
        String reverse = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse += chars[i];
        }
        return reverse;
    }

    // 3.利用 String 的 CharAt 方法取出字符串中的各个字符:
    public static String reverse3(String str) {
        String reverse = "";
        int length = str.length();
        for (int i = 0; i < length; i++) {
            reverse = str.charAt(i) + reverse;
        }
        return reverse;
    }

}
