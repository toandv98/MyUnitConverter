package vn.com.toandv98.unitconverter.data.local;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vn.com.toandv98.unitconverter.data.entities.ConversionRoom;
import vn.com.toandv98.unitconverter.data.entities.UnitRoom;

@Dao
public interface ConversionDao {

    @Query("select * from conversion_item")
    List<ConversionRoom> getConversions();

    @Query("select * from unit_local where conversion_id = :id")
    List<UnitRoom> getUnitsByConversionId(int id);

    @Query("select * from conversion_item where id = :id")
    ConversionRoom getConversionById(int id);

    @Update
    void updateLocalRates(List<UnitRoom> unitRooms);
}
