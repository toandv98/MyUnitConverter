package vn.com.toandv98.unitconverter.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

import vn.com.toandv98.unitconverter.data.entities.ConversionItem;

public final class AppUtils {
    private AppUtils() {
    }

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static int getTitleResFromId(int id) {
        for (ConversionItem item : ConversionUtils.getInstance().getConversionItems()) {
            if (item.getId() == id) return item.getTitleRes();
        }
        return -1;
    }
}
