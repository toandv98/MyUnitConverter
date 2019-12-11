package vn.com.toandv98.unitconverter.data.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ConversionWithUnit {
    @Embedded
    public ConversionItemRoom itemLocal;

    @Relation(parentColumn = "id", entityColumn = "conversion_id", entity = UnitRoom.class)
    public List<UnitRoom> unitRooms;
}
