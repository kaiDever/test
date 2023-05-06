package org.example.knowledge;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternStudy {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("(java)");
        Matcher matcher = pattern.matcher("12java自由java之路");

        int groupCount = matcher.groupCount();
        System.out.println(groupCount);
        groupCount = matcher.groupCount();
        System.out.println(groupCount);
//
//        while (matcher.find()) {
//            int start = matcher.start();
//            int end = matcher.end();
//            String group = matcher.group();
//            System.out.println("start:end:group-->" + start + ":" + end + ":" + group);
//        }

    }
}
