package vn.com.toandv98.unitconverter.data.entities;

import java.util.List;

public class ConversionItem {
    private int id;
    private int imageRes;
    private int titleRes;
    private List<Unit> units;

    public ConversionItem(int id, int imageRes, int titleRes, List<Unit> units) {
        this.id = id;
        this.imageRes = imageRes;
        this.titleRes = titleRes;
        this.units = units;
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void setUnits(List<Unit> units) {
        this.units = units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public int getTitleRes() {
        return titleRes;
    }

    public void setTitleRes(int titleRes) {
        this.titleRes = titleRes;
    }
}
