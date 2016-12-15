/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.easynet.gwt.client.util;

import java.util.Arrays;

/**
 *
 * @author geoleite
 */
public class EasyText {

    public static String removeCharSymbols(String value) {
        String regex = "\\(|\\[|-|_|,|\\.|>|`|´|<|~|^|/|\\?|°|\\|:|;|§|\\||!|¹|²|³|£|¢|¬|§|º|@|#|%|¨|&|\\*|\\+|{|}|\\]|\\)";
        if (value != null) {
            value = value.replaceAll(regex, "");
        }
        return value;
    }

    public static String padLeft(String s, char c, int qnt) {
        int total = qnt - s.length();
        if (total < 0) {
            return s.substring(0, qnt);
        }
        return completeChar(true, s, c, total);
    }

    public static String padRight(String s, char c, int qnt) {
        StringBuffer sb = new StringBuffer(s);
        int total = qnt - s.length();
        if (total < 0) {
            return s.substring(0, qnt);
        }
        return completeChar(false, s, c, total);
    }

    private static String completeChar(boolean left, String s, char c, int qnt) {
        String str = "";
        for (int i = 0; i < qnt; i++) {
            str += c + "";
        }
        if (left) {
            str = str + s;
        } else {
            str = s + str;
        }
        return str;
    }
//    public static String padLeftFormat(String s, char c, int n) {
//        return String.format("%1$#" + n + "s", s).replace(' ', c);
//    }
//
//    public static String padRightFormat(String s, char c, int n) {
//        return String.format("%1$" + n + "s", s).replace(' ', c);
//    }
}
