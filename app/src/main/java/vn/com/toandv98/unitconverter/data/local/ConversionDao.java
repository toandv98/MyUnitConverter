package vn.com.toandv98.unitconverter.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

import vn.com.toandv98.unitconverter.data.entities.ConversionRoom;
import vn.com.toandv98.unitconverter.data.entities.CustomConversion;
import vn.com.toandv98.unitconverter.data.entities.CustomUnit;
import vn.com.toandv98.unitconverter.data.entities.UnitRoom;

@Dao
public abstract class ConversionDao {

    //region Constants conversions
    @Query("select * from conversion_item")
    public abstract List<ConversionRoom> getConversions();

    @Query("select * from unit_local where conversion_id = :id")
    public abstract List<UnitRoom> getUnitsByConversionId(int id);

    @Query("select * from conversion_item where id = :id")
    public abstract ConversionRoom getConversionById(int id);

    @Update
    public abstract void updateLocalRates(List<UnitRoom> unitRooms);
    //endregion

    //region Custom conversions
    @Query("select * from conversion_custom order by history desc")
    public abstract List<CustomConversion> getConversionCustoms();

    @Query("select * from conversion_custom where id = :id")
    public abstract CustomConversion getConversionCustomById(int id);

    @Query("select * from unit_custom where conversion_id = :id")
    public abstract List<CustomUnit> getUnitCustomsByConversionId(int id);

    @Transaction
    public void insertConversionWithUnits(CustomConversion custom, List<CustomUnit> customUnits) {
        final long id = insertConversionCustom(custom);
        for (CustomUnit unit : customUnits) {
            unit.setConversionId((int) id);
        }
        insertUnitCustoms(customUnits);
    }

    @Transaction
    public void updateConversionWithUnits(CustomConversion custom, List<CustomUnit> newUnits) {
        updateCustomConversion(custom);
        for (CustomUnit unit : newUnits) {
            unit.setConversionId(custom.getId());
        }
        deleteUnitCustomsByConversionId(custom.getId());
        insertUnitCustoms(newUnits);
    }

    @Insert
    public abstract long insertConversionCustom(CustomConversion custom);

    @Insert
    public abstract void insertUnitCustoms(List<CustomUnit> customUnits);

    @Query("update conversion_custom set history = history + 1 where id = :id")
    public abstract void updateHistory(int id);

    @Update
    public abstract void updateCustomConversion(CustomConversion custom);

    @Transaction
    public void deleteConversions(CustomConversion custom) {
        deleteUnitCustomsByConversionId(custom.getId());
        deleteConversionCustom(custom);
    }

    @Query("delete from unit_custom where conversion_id = :id")
    public abstract void deleteUnitCustomsByConversionId(int id);

    @Delete
    public abstract void deleteUnitCustom(CustomUnit customUnit);

    @Delete
    public abstract void deleteConversionCustom(CustomConversion customConversion);
    //endregion
}
