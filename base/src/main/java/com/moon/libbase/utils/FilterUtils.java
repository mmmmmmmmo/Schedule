package com.moon.libbase.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ry
 * @date 2019-05-16
 * 字符串验证
 */
public class FilterUtils {
    /**
     * Emoji表情过滤，会造成 maxlength设置无效
     *
     * @param string
     * @return
     */
    public static InputFilter emojiFilter = new InputFilter() {
//        Pattern emoji = Pattern.compile(
//                " [^\\w[|[\\u4e00-\\u9fa5\\u001f-\\u007f]|[.,!\"'?$%@:;~\\-()，。！？、“”：；～（）]]]",
//                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        Pattern emoji = Pattern.compile(
                "[^\\w\\u4e00-\\u9fa5\\u001f-\\u007f\\u3002\\uff1b\\uff0c\\uff1a\\u201c\\u201d\\uff08\\uff09\\u3001\\uff1f\\u300a\\u300b\\n\\uff01\\u2014]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
                                   int dend) {
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                return "";
            }
            return null;
        }
    };

    /**
     * 手机号匹配
     * 规则: 以1开头的11位数字
     */
    public static boolean isPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        return phone.matches("^[1][0-9]{10}$");
    }

    /**
     * 密码匹配
     * 规则：6-12位中英文或数字
     */
    public static boolean isPassword(String password) {
        if (TextUtils.isEmpty(password)) {
            return false;
        }
        return password.matches("^\\w{5,15}$");
    }

    public static boolean isName(String name) {
        if (TextUtils.isEmpty(name)) {
            return false;
        }
        return name.matches("^\\w{1,10}$");
    }
}
