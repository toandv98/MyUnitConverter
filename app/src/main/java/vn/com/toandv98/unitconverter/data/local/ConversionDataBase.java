package vn.com.toandv98.unitconverter.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import vn.com.toandv98.unitconverter.data.entities.ConversionItemRoom;
import vn.com.toandv98.unitconverter.data.entities.UnitRoom;

@Database(entities = {ConversionItemRoom.class, UnitRoom.class}, exportSchema = false, version = 1)
public abstract class ConversionDataBase extends RoomDatabase {

    private static final String DB_NAME = "conversion_database";
    private static ConversionDataBase INSTANCE = null;

    public abstract ConversionDao conversionDao();

    public static ConversionDataBase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, ConversionDataBase.class, DB_NAME)
                    .createFromAsset(DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
