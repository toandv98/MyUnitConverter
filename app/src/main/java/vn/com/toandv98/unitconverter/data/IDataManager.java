package vn.com.toandv98.unitconverter.data;

import java.util.List;

import vn.com.toandv98.unitconverter.data.entities.Conversion;
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

    List<Unit> getUnitsByConversionId(int id);

    Conversion getConversionById(int id);
}
