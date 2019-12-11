package vn.com.toandv98.unitconverter.data.local;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import vn.com.toandv98.unitconverter.data.entities.ConversionWithUnit;

@Dao
public interface ConversionDao {
    @Transaction
    @Query("select * from conversion_item")
    List<ConversionWithUnit> getConversionWithUnits();
}
