package vn.com.toandv98.unitconverter.utils;

import vn.com.toandv98.unitconverter.data.entities.Unit;

public final class StateUtils {
    private static int CONVERSION_ID;
    private static int CURRENT_INPUT_POSITION;
    private static int CURRENT_RESULT_POSITION;
    private static CharSequence CURRENT_INPUT;
    private static Unit INPUT_UNIT;
    private static Unit RESULT_UNIT;

    private StateUtils() {
    }

    public static void setCurrentState(int conversionId, int inputPosition,
                                       int resultPosition, CharSequence inputValue, Unit inputUnit, Unit resultUnit) {
        CONVERSION_ID = conversionId;
        CURRENT_INPUT_POSITION = inputPosition;
        CURRENT_RESULT_POSITION = resultPosition;
        INPUT_UNIT = inputUnit;
        RESULT_UNIT = resultUnit;
        CURRENT_INPUT = inputValue;
    }

    public static void setCurrentInput(CharSequence currentInput) {
        CURRENT_INPUT = currentInput;
    }

    public static void setInputUnit(Unit inputUnit) {
        INPUT_UNIT = inputUnit;
    }

    public static void setResultUnit(Unit resultUnit) {
        RESULT_UNIT = resultUnit;
    }

    public static void setInputUnit(Unit inputUnit, int currentInputPosition) {
        INPUT_UNIT = inputUnit;
        CURRENT_INPUT_POSITION = currentInputPosition;
    }

    public static void setResultUnit(Unit resultUnit, int currentResultPosition) {
        RESULT_UNIT = resultUnit;
        CURRENT_RESULT_POSITION = currentResultPosition;
    }

    public static int getConversionId() {
        return CONVERSION_ID;
    }

    public static int getCurrentInputPosition() {
        return CURRENT_INPUT_POSITION;
    }

    public static int getCurrentResultPosition() {
        return CURRENT_RESULT_POSITION;
    }

    public static CharSequence getCurrentInput() {
        return CURRENT_INPUT;
    }

    public static Unit getInputUnit() {
        return INPUT_UNIT;
    }

    public static Unit getResultUnit() {
        return RESULT_UNIT;
    }

    public static void swapUnit() {
        Unit temp = INPUT_UNIT;
        INPUT_UNIT = RESULT_UNIT;
        RESULT_UNIT = temp;
    }
}
