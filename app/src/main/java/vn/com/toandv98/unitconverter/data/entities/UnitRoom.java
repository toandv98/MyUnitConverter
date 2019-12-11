package vn.com.toandv98.unitconverter.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "unit_local")
public class UnitRoom {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "label_res_name")
    private String labelRes;

    @ColumnInfo(name = "drawable_res_Name")
    private String drawableRes;

    @ColumnInfo(name = "to_base")
    private double toBase;

    @ColumnInfo(name = "from_base")
    private double fromBase;

    @ColumnInfo(name = "conversion_id")
    private int conversionId;

    public int getConversionId() {
        return conversionId;
    }

    public void setConversionId(int conversionId) {
        this.conversionId = conversionId;
    }

    public UnitRoom(int id, String labelRes, String drawableRes, double toBase, double fromBase, int conversionId) {
        this.id = id;
        this.labelRes = labelRes;
        this.drawableRes = drawableRes;
        this.toBase = toBase;
        this.fromBase = fromBase;
        this.conversionId = conversionId;
    }

    public String getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(String drawableRes) {
        this.drawableRes = drawableRes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabelRes() {
        return labelRes;
    }

    public void setLabelRes(String labelRes) {
        this.labelRes = labelRes;
    }

    public double getToBase() {
        return toBase;
    }

    public void setToBase(double toBase) {
        this.toBase = toBase;
    }

    public double getFromBase() {
        return fromBase;
    }

    public void setFromBase(double fromBase) {
        this.fromBase = fromBase;
    }
}
