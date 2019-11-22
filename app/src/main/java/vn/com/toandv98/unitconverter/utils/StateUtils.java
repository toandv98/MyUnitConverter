package vn.com.toandv98.unitconverter.utils;

import vn.com.toandv98.unitconverter.data.entities.Unit;

public final class StateUtils {
    private static int CONVERSION_ID;
    private static CharSequence CURRENT_INPUT;
    private static Unit INPUT_UNIT;
    private static Unit RESULT_UNIT;

    private StateUtils() {
    }

    public static int getConversionId() {
        return CONVERSION_ID;
    }

    public static void setConversionId(int conversionId) {
        CONVERSION_ID = conversionId;
    }

    public static CharSequence getCurrentInput() {
        return CURRENT_INPUT;
    }

    public static void setCurrentInput(CharSequence currentInput) {
        CURRENT_INPUT = currentInput;
    }

    public static Unit getInputUnit() {
        return INPUT_UNIT;
    }

    public static void setInputUnit(Unit inputUnit) {
        INPUT_UNIT = inputUnit;
    }

    public static Unit getResultUnit() {
        return RESULT_UNIT;
    }

    public static void setResultUnit(Unit resultUnit) {
        RESULT_UNIT = resultUnit;
    }

    public static void swapUnit() {
        Unit temp = INPUT_UNIT;
        INPUT_UNIT = RESULT_UNIT;
        RESULT_UNIT = temp;
    }

}
