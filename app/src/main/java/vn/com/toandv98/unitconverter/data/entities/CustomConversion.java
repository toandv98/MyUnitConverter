package vn.com.toandv98.unitconverter.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "conversion_custom")
public class CustomConversion {
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
}
