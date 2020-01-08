package vn.com.toandv98.unitconverter.data.entities;

public class Conversion {
    private int id;
    private int imageRes;
    private int titleRes;
    private String titleCustom;

    public Conversion(int id, int imageRes, int titleRes, String titleCustom) {
        this.id = id;
        this.imageRes = imageRes;
        this.titleRes = titleRes;
        this.titleCustom = titleCustom;
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

    public String getTitleCustom() {
        return titleCustom;
    }

    public void setTitleCustom(String titleCustom) {
        this.titleCustom = titleCustom;
    }
}
