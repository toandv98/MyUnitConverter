package vn.com.toandv98.unitconverter.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "unit_custom")
public class CustomUnit implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    private String label;

    @ColumnInfo(name = "from_base")
    private double fromBase;

    @ColumnInfo(name = "to_base")
    private double toBase;

    @ColumnInfo(name = "conversion_id")
    private int conversionId;

    public CustomUnit(String label, double fromBase, double toBase, int conversionId) {
        this.label = label;
        this.fromBase = fromBase;
        this.toBase = toBase;
        this.conversionId = conversionId;
    }

    public int getConversionId() {
        return conversionId;
    }

    public void setConversionId(int conversionId) {
        this.conversionId = conversionId;
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

    public double getFromBase() {
        return fromBase;
    }

    public void setFromBase(double fromBase) {
        this.fromBase = fromBase;
    }

    public double getToBase() {
        return toBase;
    }

    public void setToBase(double toBase) {
        this.toBase = toBase;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.label);
        dest.writeDouble(this.fromBase);
        dest.writeDouble(this.toBase);
        dest.writeInt(this.conversionId);
    }

    protected CustomUnit(Parcel in) {
        this.id = in.readInt();
        this.label = in.readString();
        this.fromBase = in.readDouble();
        this.toBase = in.readDouble();
        this.conversionId = in.readInt();
    }

    public static final Parcelable.Creator<CustomUnit> CREATOR = new Parcelable.Creator<CustomUnit>() {
        @Override
        public CustomUnit createFromParcel(Parcel source) {
            return new CustomUnit(source);
        }

        @Override
        public CustomUnit[] newArray(int size) {
            return new CustomUnit[size];
        }
    };
}
