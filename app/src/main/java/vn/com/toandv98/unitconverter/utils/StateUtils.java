package vn.com.toandv98.unitconverter.utils;

import vn.com.toandv98.unitconverter.data.entities.Unit;

import static vn.com.toandv98.unitconverter.utils.Constrants.DEFAULT_INPUT;
import static vn.com.toandv98.unitconverter.utils.Constrants.DEFAULT_POSITION;

public final class StateUtils {

    public static int CURRENT_INPUT_POSITION;
    public static int CURRENT_RESULT_POSITION;
    public static CharSequence CURRENT_INPUT;
    public static Unit CURRENT_INPUT_UNIT;
    public static Unit CURRENT_RESULT_UNIT;

    private StateUtils() {
    }

    public static void setDefaultState(Unit unit) {
        CURRENT_INPUT_POSITION = DEFAULT_POSITION;
        CURRENT_RESULT_POSITION = DEFAULT_POSITION;
        CURRENT_INPUT_UNIT = unit;
        CURRENT_RESULT_UNIT = unit;
        CURRENT_INPUT = DEFAULT_INPUT;
    }

    public static void swapUnit() {
        Unit temp = CURRENT_INPUT_UNIT;
        CURRENT_INPUT_UNIT = CURRENT_RESULT_UNIT;
        CURRENT_RESULT_UNIT = temp;
    }
}
