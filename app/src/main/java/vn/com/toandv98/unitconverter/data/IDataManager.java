package vn.com.toandv98.unitconverter.data;

import java.util.List;
import java.util.Map;

import vn.com.toandv98.unitconverter.data.entities.ConversionItem;
import vn.com.toandv98.unitconverter.data.entities.Unit;

public interface IDataManager {

    void updateFromRemote();

    Map<String, Double> getLastRates();

    List<Unit> getCurrencyUnits();

    List<ConversionItem> getConversions();

    ConversionItem getConversion(int id);
}
