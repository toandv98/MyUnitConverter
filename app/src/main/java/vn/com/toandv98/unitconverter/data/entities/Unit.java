package vn.com.toandv98.unitconverter.data.entities;

public class Unit {
    private int id, labelRes, drawableRes;
    private double toBase, fromBase;

    public Unit(int id, int labelRes, int drawableRes, double toBase, double fromBase) {
        this.id = id;
        this.labelRes = labelRes;
        this.drawableRes = drawableRes;
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    public int getDrawableRes() {
        return drawableRes;
    }

    public void setDrawableRes(int drawableRes) {
        this.drawableRes = drawableRes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabelRes() {
        return labelRes;
    }

    public void setLabelRes(int labelRes) {
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
