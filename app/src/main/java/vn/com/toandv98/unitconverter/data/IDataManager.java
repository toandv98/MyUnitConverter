package vn.com.toandv98.unitconverter.data;

import java.util.List;

import vn.com.toandv98.unitconverter.data.entities.Conversion;
import vn.com.toandv98.unitconverter.data.entities.CustomConversion;
import vn.com.toandv98.unitconverter.data.entities.CustomUnit;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.data.entities.UnitRoom;

public interface IDataManager {

    interface RemoteCallBack {
        void onSuccess(List<UnitRoom> unitRooms);

        void onFailure(String msg);
    }

    void updateFromRemote();

    void fetchLastRates(RemoteCallBack callBack);

    List<Conversion> getConversions();

    List<CustomConversion> getCustomConversions();

    List<Unit> getUnitsByConversionId(int id);

    List<CustomUnit> getUnitCustomsByConversionId(int id);

    Conversion getConversionById(int id);

    void insertConversionWithUnits(CustomConversion custom, List<CustomUnit> customUnits);

    void updateConversionWithUnits(CustomConversion custom, List<CustomUnit> newUnits);

    void updateHistory(int id);

    void deleteConversions(CustomConversion custom);
}
