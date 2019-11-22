package vn.com.toandv98.unitconverter.utils;

import java.util.ArrayList;
import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.entities.ConversionItem;

import static vn.com.toandv98.unitconverter.utils.Constrants.AREA;
import static vn.com.toandv98.unitconverter.utils.Constrants.CURRENCY;
import static vn.com.toandv98.unitconverter.utils.Constrants.ENERGY;
import static vn.com.toandv98.unitconverter.utils.Constrants.LENGTH;
import static vn.com.toandv98.unitconverter.utils.Constrants.SI;
import static vn.com.toandv98.unitconverter.utils.Constrants.SOUND;
import static vn.com.toandv98.unitconverter.utils.Constrants.SPEED;
import static vn.com.toandv98.unitconverter.utils.Constrants.STORAGE;
import static vn.com.toandv98.unitconverter.utils.Constrants.TEMPERATURE;
import static vn.com.toandv98.unitconverter.utils.Constrants.TIME;
import static vn.com.toandv98.unitconverter.utils.Constrants.VOLUME;
import static vn.com.toandv98.unitconverter.utils.Constrants.WEIGHT;

public final class ConversionUtils {
    private static ConversionUtils INSTANCE = null;
    private List<ConversionItem> conversionItems;

    public static synchronized ConversionUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConversionUtils();
        }
        return INSTANCE;
    }

    private ConversionUtils() {
        getConversions();
    }

    public List<ConversionItem> getConversionItems() {
        return conversionItems;
    }

    private void getConversions() {
        conversionItems = new ArrayList<>();
        conversionItems.add(new ConversionItem(LENGTH, R.drawable.ic_length, R.string.item_title_length));
        conversionItems.add(new ConversionItem(WEIGHT, R.drawable.ic_weight, R.string.item_title_weight));
        conversionItems.add(new ConversionItem(VOLUME, R.drawable.ic_volume, R.string.item_title_volume));
        conversionItems.add(new ConversionItem(SPEED, R.drawable.ic_speed, R.string.item_title_speed));
        conversionItems.add(new ConversionItem(AREA, R.drawable.ic_area, R.string.item_title_area));
        conversionItems.add(new ConversionItem(TEMPERATURE, R.drawable.ic_temperature, R.string.item_title_temperature));
        conversionItems.add(new ConversionItem(TIME, R.drawable.ic_time, R.string.item_title_time));
        conversionItems.add(new ConversionItem(STORAGE, R.drawable.ic_storage, R.string.item_title_storage));
        conversionItems.add(new ConversionItem(SOUND, R.drawable.ic_sound, R.string.item_title_sound));
        conversionItems.add(new ConversionItem(SI, R.drawable.ic_si, R.string.item_title_si));
        conversionItems.add(new ConversionItem(ENERGY, R.drawable.ic_energy, R.string.item_title_energy));
        conversionItems.add(new ConversionItem(CURRENCY, R.drawable.ic_currency, R.string.item_title_currency));
    }
}
