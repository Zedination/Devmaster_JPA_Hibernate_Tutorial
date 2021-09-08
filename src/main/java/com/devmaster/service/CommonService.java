package com.devmaster.service;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class CommonService {

    /**
     * Hàm bỏ dấu tiếng Việt
     *
     * @param nội dung cần bỏ dấu
     * @return nội dung sau khi đã bỏ dấu
     */
    public String removeAccent(String s) {
        s = s.toLowerCase();
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        return temp.replaceAll("đ", "d");
    }

}
