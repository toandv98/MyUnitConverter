package vn.com.toandv98.unitconverter.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import vn.com.toandv98.unitconverter.data.entities.Unit;

import static vn.com.toandv98.unitconverter.utils.Constrants.CELSIUS;
import static vn.com.toandv98.unitconverter.utils.Constrants.DELISLE;
import static vn.com.toandv98.unitconverter.utils.Constrants.FAHRENHEIT;
import static vn.com.toandv98.unitconverter.utils.Constrants.GAS_MARK;
import static vn.com.toandv98.unitconverter.utils.Constrants.KELVIN;
import static vn.com.toandv98.unitconverter.utils.Constrants.NEWTON;
import static vn.com.toandv98.unitconverter.utils.Constrants.RANKINE;
import static vn.com.toandv98.unitconverter.utils.Constrants.REAUMUR;
import static vn.com.toandv98.unitconverter.utils.Constrants.ROMER;
import static vn.com.toandv98.unitconverter.utils.Constrants.TEMPERATURE;

public final class ConvertUtils {

    private ConvertUtils() {
    }

    public static String convert(double value) {

        Unit from = StateUtils.getInputUnit();
        Unit to = StateUtils.getResultUnit();
        double result = value;

        if (StateUtils.getConversionId() == TEMPERATURE) {
            result = convertTemperatureValue(value, from, to);
        } else if (from.getId() != to.getId()) {
            BigDecimal multiplier = new BigDecimal(from.getToBase()).multiply(new BigDecimal(to.getFromBase()));
            BigDecimal bdResult = new BigDecimal(value).multiply(multiplier);
            result = bdResult.doubleValue();
        }

        return getDecimalFormat().format(result);
    }

    private static DecimalFormat getDecimalFormat() {
        DecimalFormat formatter = new DecimalFormat();
        formatter.setMaximumFractionDigits(5);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        formatter.setGroupingUsed(true);
        symbols.setGroupingSeparator(',');
        formatter.setDecimalFormatSymbols(symbols);
        return formatter;
    }

    private static double convertTemperatureValue(double value, Unit from, Unit to) {
        double result = value;
        if (from.getId() != to.getId()) {
            switch (to.getId()) {
                case (CELSIUS):
                    result = toCelsius(from.getId(), value);
                    break;

                case (FAHRENHEIT):
                    result = toFahrenheit(from.getId(), value);
                    break;

                case (KELVIN):
                    result = toKelvin(from.getId(), value);
                    break;

                case (RANKINE):
                    result = toRankine(from.getId(), value);
                    break;

                case (DELISLE):
                    result = toDelisle(from.getId(), value);
                    break;

                case (NEWTON):
                    result = toNewton(from.getId(), value);
                    break;

                case (REAUMUR):
                    result = toReaumur(from.getId(), value);
                    break;

                case (ROMER):
                    result = toRomer(from.getId(), value);
                    break;

                case (GAS_MARK):
                    result = toGasMark(from.getId(), value);
                    break;
            }
        }

        return result;
    }

    private static double toCelsius(int fromId, double value) {
        double result = value;

        switch (fromId) {
            case (FAHRENHEIT):    // F to C
                result = (value - 32) * 5 / 9;
                break;

            case (KELVIN):    // K to C
                result = value - 273.15;
                break;

            case (RANKINE):    // R to C
                result = (value - 491.67) * 5 / 9;
                break;

            case (DELISLE):    // D to C
                result = 100 - value * 2 / 3;
                break;

            case (NEWTON):    //N to C
                result = value * 100 / 33;
                break;

            case (REAUMUR):    //Re to C
                result = value * 5 / 4;
                break;

            case (ROMER):    //Ro to C
                result = (value - 7.5) * 40 / 21;
                break;

            case (GAS_MARK): //GM to C
                //Convert from GM to F, then from F to C
                result = (fromGasMark(value) - 32) * 5 / 9;
                break;
        }

        return result;
    }

    private static double toFahrenheit(int fromId, double value) {
        double result = value;

        switch (fromId) {
            case (CELSIUS):    // C to F
                result = value * 9 / 5 + 32;
                break;

            case (KELVIN):    // K to F
                result = value * 9 / 5 - 459.67;
                break;

            case (RANKINE):    // R to F
                result = value - 459.67;
                break;

            case (DELISLE):    //D to F
                result = 212 - value * 6 / 5;
                break;

            case (NEWTON):    //N to F
                result = value * 60 / 11 + 32;
                break;

            case (REAUMUR):    //Re to F
                result = value * 9 / 4 + 32;
                break;

            case (ROMER):    //Ro to F
                result = (value - 7.5) * 24 / 7 + 32;
                break;

            case (GAS_MARK):
                result = fromGasMark(value);
                break;
        }

        return result;
    }

    private static double toKelvin(int fromId, double value) {
        double result = value;

        switch (fromId) {
            case (CELSIUS):    // C to K
                result = value + 273.15;
                break;

            case (FAHRENHEIT):    // F to K
                result = (value + 459.67) * 5 / 9;
                break;

            case (RANKINE):    // R to K
                result = value * 5 / 9;
                break;

            case (DELISLE):    //D to K
                result = 373.15 - value * 2 / 3;
                break;

            case (NEWTON):    //N to K
                result = value * 100 / 33 + 273.15;
                break;

            case (REAUMUR):    //Re to K
                result = value * 5 / 4 + 273.15;
                break;

            case (ROMER):    //Ro to K
                result = (value - 7.5) * 40 / 21 + 273.15;
                break;

            case (GAS_MARK):
                result = (fromGasMark(value) + 459.67) * 5 / 9;
                break;
        }

        return result;
    }

    private static double toRankine(int fromId, double value) {
        double result = value;

        switch (fromId) {
            case (CELSIUS):    // C to R
                result = (value + 273.15) * 9 / 5;
                break;

            case (FAHRENHEIT):    // F to R
                result = value + 459.67;
                break;

            case (KELVIN):    // K to R
                result = value * 9 / 5;
                break;

            case (DELISLE):    //D to R
                result = 671.67 - value * 6 / 5;
                break;

            case (NEWTON):    //N to R
                result = value * 60 / 11 + 491.67;
                break;

            case (REAUMUR):    //Re to R
                result = value * 9 / 4 + 491.67;
                break;

            case (ROMER):    //Ro to R
                result = (value - 7.5) * 24 / 7 + 491.67;
                break;

            case (GAS_MARK):
                result = fromGasMark(value) + 459.67;
                break;
        }

        return result;
    }

    private static double toDelisle(int fromId, double value) {
        double result = value;

        switch (fromId) {
            case (CELSIUS):    // C to D
                result = (100 - value) * 1.5;
                break;

            case (FAHRENHEIT):    // F to D
                result = (212 - value) * 5 / 6;
                break;

            case (KELVIN):    // K to D
                result = (373.15 - value) * 1.5;
                break;

            case (RANKINE):    // R to D
                result = (671.67 - value) * 5 / 6;
                break;

            case (NEWTON):    //N to D
                result = (33 - value) * 50 / 11;
                break;

            case (REAUMUR):    //Re to D
                result = (80 - value) * 1.875;
                break;

            case (ROMER):    //Ro to D
                result = (60 - value) * 20 / 7;
                break;

            case (GAS_MARK):
                result = (212 - fromGasMark(value)) * 5 / 6;
                break;
        }

        return result;
    }

    private static double toNewton(int fromId, double value) {
        double result = value;

        switch (fromId) {
            case (CELSIUS):    // C to N
                result = value * 33 / 100;
                break;

            case (FAHRENHEIT):    // F to N
                result = (value - 32) * 11 / 60;
                break;

            case (KELVIN):    // K to N
                result = (value - 273.15) * 33 / 100;
                break;

            case (RANKINE):    // R to N
                result = (value - 491.67) * 11 / 60;
                break;

            case (DELISLE):    //D to N
                result = 33 - value * 11 / 50;
                break;

            case (REAUMUR):    //Re to N
                result = value * 33 / 80;
                break;

            case (ROMER):    //Ro to N
                result = (value - 7.5) * 22 / 35;
                break;

            case (GAS_MARK):
                result = (fromGasMark(value) - 32) * 11 / 60;
                break;
        }

        return result;
    }

    private static double toReaumur(int fromId, double value) {
        double result = value;

        switch (fromId) {
            case (CELSIUS):    // C to Re
                result = value * 4 / 5;
                break;

            case (FAHRENHEIT):    // F to Re
                result = (value - 32) * 4 / 9;
                break;

            case (KELVIN):    // K to Re
                result = (value - 273.15) * 4 / 5;
                break;

            case (RANKINE):    // R to Re
                result = (value - 491.67) * 4 / 9;
                break;

            case (DELISLE):    //D to Re
                result = 80 - value * 8 / 15;
                break;

            case (NEWTON):    //N to Re
                result = value * 80 / 33;
                break;

            case (ROMER):    //Ro to Re
                result = (value - 7.5) * 32 / 21;
                break;

            case (GAS_MARK):
                result = (fromGasMark(value) - 32) * 4 / 9;
                break;
        }

        return result;
    }

    private static double toRomer(int fromId, double value) {
        double result = value;

        switch (fromId) {
            case (CELSIUS):    // C to Ro
                result = value * 21 / 40 + 7.5;
                break;

            case (FAHRENHEIT):    // F to Ro
                result = (value - 32) * 7 / 24 + 7.5;
                break;

            case (KELVIN):    // K to Ro
                result = (value - 273.15) * 21 / 40 + 7.5;
                break;

            case (RANKINE):    // R to Ro
                result = (value - 491.67) * 7 / 24 + 7.5;
                break;

            case (DELISLE):    //D to Ro
                result = 60 - value * 7 / 20;
                break;

            case (NEWTON):    //N to Ro
                result = value * 35 / 22 + 7.5;
                break;

            case (REAUMUR):    //Re to Ro
                result = value * 21 / 32 + 7.5;
                break;

            case (GAS_MARK):
                result = (fromGasMark(value) - 32) * 7 / 24 + 7.5;
                break;
        }

        return result;
    }

    private static double toGasMark(int fromId, double value) {
        //Convert incoming temperature to Fahrenheit, then convert from F to Gas Mark
        double resultF = toFahrenheit(fromId, value);
        double resultGM = 0;

        if (resultF >= 275) {
            resultGM = 0.04 * resultF - 10;
        } else if (resultF < 275) {
            resultGM = 0.01 * resultF - 2;
        }

        if (resultGM < 0) resultGM = 0;

        return resultGM;
    }

    private static double fromGasMark(double value) {
        double resultF = 0;

        //Convert incoming Gas Mark to Fahrenheit, which will then be subequently converted to desired unit
        if (value >= 1) {
            resultF = 25 * value + 250;
        } else if (value < 1) {
            resultF = 100 * value + 200;
        }

        return resultF;
    }
}
