package vn.com.toandv98.unitconverter.data.entities;

public class ConversionItem {
    private int id;
    private int imageRes;
    private int titleRes;

    public ConversionItem(int id, int imageRes, int titleRes) {
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
