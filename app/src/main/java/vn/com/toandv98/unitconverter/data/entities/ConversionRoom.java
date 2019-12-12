package vn.com.toandv98.unitconverter.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "conversion_item")
public class ConversionRoom {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "image_res_name")
    private String imageRes;

    @ColumnInfo(name = "title_res_name")
    private String titleRes;

    public ConversionRoom(int id, String imageRes, String titleRes) {
        this.id = id;
        this.imageRes = imageRes;
        this.titleRes = titleRes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageRes() {
        return imageRes;
    }

    public void setImageRes(String imageRes) {
        this.imageRes = imageRes;
    }

    public String getTitleRes() {
        return titleRes;
    }

    public void setTitleRes(String titleRes) {
        this.titleRes = titleRes;
    }
}

