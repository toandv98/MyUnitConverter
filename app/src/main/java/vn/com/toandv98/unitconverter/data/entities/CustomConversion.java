package vn.com.toandv98.unitconverter.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "conversion_custom")
public class CustomConversion implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String label;

    @ColumnInfo
    private int history;

    public CustomConversion(String label, int history) {
        this.label = label;
        this.history = history;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.label);
        dest.writeInt(this.history);
    }

    protected CustomConversion(Parcel in) {
        this.id = in.readInt();
        this.label = in.readString();
        this.history = in.readInt();
    }

    public static final Parcelable.Creator<CustomConversion> CREATOR = new Parcelable.Creator<CustomConversion>() {
        @Override
        public CustomConversion createFromParcel(Parcel source) {
            return new CustomConversion(source);
        }

        @Override
        public CustomConversion[] newArray(int size) {
            return new CustomConversion[size];
        }
    };
}
