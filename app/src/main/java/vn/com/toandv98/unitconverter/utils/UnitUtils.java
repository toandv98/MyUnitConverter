package vn.com.toandv98.unitconverter.utils;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.entities.Unit;

import static vn.com.toandv98.unitconverter.utils.Constrants.*;

public final class UnitUtils {
    private static UnitUtils INSTANCE = null;
    private SparseArray<List<Unit>> map;

    public static synchronized UnitUtils getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UnitUtils();
        }
        return INSTANCE;
    }

    private UnitUtils() {
        map = new SparseArray<>();
        getLength();
        getWeight();
        getVolume();
        getSpeed();
        getArea();
        getTemperature();
        getTime();
        getStorage();
        getSound();
        getSI();
        getEnergy();
    }

    public List<Unit> getUnitById(int id) {
        return map.get(id);
    }

    private void getEnergy() {
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(JOULE, R.string.joule, R.drawable.ic_energy, 1.0, 1.0));
        units.add(new Unit(KILOJOULE, R.string.kilojoule, R.drawable.ic_energy, 1000.0, 0.001));
        units.add(new Unit(CALORIE, R.string.calorie, R.drawable.ic_energy, 4.184, 0.2390057361376673040153));
        units.add(new Unit(KILOCALORIE, R.string.kilocalorie, R.drawable.ic_energy, 4184.0, 0.0002390057361376673040153));
        units.add(new Unit(BTU, R.string.btu, R.drawable.ic_energy, 1055.05585262, 0.0009478171203133172000128));
        units.add(new Unit(FT_LBF, R.string.ft_lbF, R.drawable.ic_energy, 1.3558179483314004, 0.7375621494575464935503));
        units.add(new Unit(IN_LBF, R.string.in_lbF, R.drawable.ic_energy, 0.1129848290276167, 8.850745793490557922604));
        units.add(new Unit(KILOWATT_HOUR, R.string.kilowatt_hour, R.drawable.ic_energy, 3600000.0, 0.0000002777777777777777777778));
        map.put(ENERGY, units);
    }

    private void getSI() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(TERA, R.string.tera, R.drawable.ic_si, 100000000000L, 0.00000000001));
        units.add(new Unit(GIGA, R.string.giga, R.drawable.ic_si, 10000000000L, 0.0000000001));
        units.add(new Unit(MEGA, R.string.mega, R.drawable.ic_si, 100000, 0.00001));
        units.add(new Unit(KILO, R.string.kilo, R.drawable.ic_si, 100, 0.01));
        units.add(new Unit(HECTO, R.string.hecto, R.drawable.ic_si, 10, 0.1));
        units.add(new Unit(DEKA, R.string.deka, R.drawable.ic_si, 1.0, 1.0));
        units.add(new Unit(DECI, R.string.deci, R.drawable.ic_si, 0.01, 100));
        units.add(new Unit(CENTI, R.string.centi, R.drawable.ic_si, 0.001, 1000));
        units.add(new Unit(MILLI, R.string.milli, R.drawable.ic_si, 0.0001, 10000));
        units.add(new Unit(MICRO, R.string.micro, R.drawable.ic_si, 0.0000001, 10000000));
        units.add(new Unit(NANO, R.string.nano, R.drawable.ic_si, 0.0000000001, 10000000000L));
        units.add(new Unit(PICO, R.string.pico, R.drawable.ic_si, 0.0000000000001, 10000000000000L));
        map.put(SI, units);
    }

    private void getSound() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(BEL, R.string.bel, R.drawable.ic_sound, 1.0, 1.0));
        units.add(new Unit(DECIBEL, R.string.decibel, R.drawable.ic_sound, 0.1, 10));
        units.add(new Unit(NEPER, R.string.neper, R.drawable.ic_sound, 0.869, 1.151));
        map.put(SOUND, units);
    }

    private void getStorage() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(BIT, R.string.bit, R.drawable.ic_storage, 0.00000011920928955078, 8388608.0));
        units.add(new Unit(BYTE, R.string.Byte, R.drawable.ic_storage, 0.00000095367431640625, 1048576.0));
        units.add(new Unit(KILOBIT, R.string.kilobit, R.drawable.ic_storage, 0.0001220703125, 8192.0));
        units.add(new Unit(KILOBYTE, R.string.kilobyte, R.drawable.ic_storage, 0.0009765625, 1024.0));
        units.add(new Unit(MEGABIT, R.string.megabit, R.drawable.ic_storage, 0.125, 8.0));
        units.add(new Unit(MEGABYTE, R.string.megabyte, R.drawable.ic_storage, 1.0, 1.0));
        units.add(new Unit(GIGABIT, R.string.gigabit, R.drawable.ic_storage, 128.0, 0.0078125));
        units.add(new Unit(GIGABYTE, R.string.gigabyte, R.drawable.ic_storage, 1024.0, 0.0009765625));
        units.add(new Unit(TERABIT, R.string.terabit, R.drawable.ic_storage, 131072.0, 0.00000762939453125));
        units.add(new Unit(TERABYTE, R.string.terabyte, R.drawable.ic_storage, 1048576.0, 0.00000095367431640625));
        map.put(STORAGE, units);
    }

    private void getTime() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(YEAR, R.string.year, R.drawable.ic_time, 31536000.0, 0.0000000317097919837645865));
        units.add(new Unit(MONTH, R.string.month, R.drawable.ic_time, 2628000.0, 0.0000003805175));
        units.add(new Unit(WEEK, R.string.week, R.drawable.ic_time, 604800.0, 0.00000165343915343915344));
        units.add(new Unit(DAY, R.string.day, R.drawable.ic_time, 86400.0, 0.0000115740740740740741));
        units.add(new Unit(HOUR, R.string.hour, R.drawable.ic_time, 3600.0, 0.000277777777777777778));
        units.add(new Unit(MINUTE, R.string.minute, R.drawable.ic_time, 60.0, 0.0166666666666666667));
        units.add(new Unit(SECOND, R.string.second, R.drawable.ic_time, 1.0, 1.0));
        units.add(new Unit(MILLISECOND, R.string.millisecond, R.drawable.ic_time, 0.001, 1000.0));
        units.add(new Unit(NANOSECOND, R.string.nanosecond, R.drawable.ic_time, 0.000000001, 1000000000.0));
        map.put(TIME, units);
    }

    private void getTemperature() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(CELSIUS, R.string.celsius, R.drawable.ic_temperature, 0.0, 0.0));
        units.add(new Unit(FAHRENHEIT, R.string.fahrenheit, R.drawable.ic_temperature, 0.0, 0.0));
        units.add(new Unit(KELVIN, R.string.kelvin, R.drawable.ic_temperature, 0.0, 0.0));
        units.add(new Unit(RANKINE, R.string.rankine, R.drawable.ic_temperature, 0.0, 0.0));
        units.add(new Unit(DELISLE, R.string.delisle, R.drawable.ic_temperature, 0.0, 0.0));
        units.add(new Unit(NEWTON, R.string.newton, R.drawable.ic_temperature, 0.0, 0.0));
        units.add(new Unit(REAUMUR, R.string.reaumur, R.drawable.ic_temperature, 0.0, 0.0));
        units.add(new Unit(ROMER, R.string.romer, R.drawable.ic_temperature, 0.0, 0.0));
        units.add(new Unit(GAS_MARK, R.string.gas_mark, R.drawable.ic_temperature, 0.0, 0.0));
        map.put(TEMPERATURE, units);
    }

    private void getArea() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(SQ_KILOMETRES, R.string.sq_kilometre, R.drawable.ic_area, 1000000.0, 0.000001));
        units.add(new Unit(SQ_METRES, R.string.sq_metre, R.drawable.ic_area, 1.0, 1.0));
        units.add(new Unit(SQ_CENTIMETRES, R.string.sq_centimetre, R.drawable.ic_area, 0.0001, 10000.0));
        units.add(new Unit(HECTARE, R.string.hectare, R.drawable.ic_area, 10000.0, 0.0001));
        units.add(new Unit(SQ_MILE, R.string.sq_mile, R.drawable.ic_area, 2589988.110336, 0.000000386102158542445847));
        units.add(new Unit(SQ_YARD, R.string.sq_yard, R.drawable.ic_area, 0.83612736, 1.19599004630108026));
        units.add(new Unit(SQ_FOOT, R.string.sq_foot, R.drawable.ic_area, 0.09290304, 10.7639104167097223));
        units.add(new Unit(SQ_INCH, R.string.sq_inch, R.drawable.ic_area, 0.00064516, 1550.00310000620001));
        units.add(new Unit(ACRE, R.string.acre, R.drawable.ic_area, 4046.8564224, 0.000247105381467165342));
        map.put(AREA, units);
    }

    private void getSpeed() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(KM_HR, R.string.km_h, R.drawable.ic_speed, 0.27777777777778, 3.6));
        units.add(new Unit(MPH, R.string.mph, R.drawable.ic_speed, 0.44704, 2.2369362920544));
        units.add(new Unit(M_S, R.string.m_s, R.drawable.ic_speed, 1.0, 1.0));
        units.add(new Unit(FPS, R.string.fps, R.drawable.ic_speed, 0.3048, 3.2808398950131));
        units.add(new Unit(KNOT, R.string.knot, R.drawable.ic_speed, 0.51444444444444, 1.9438444924406));
        map.put(SPEED, units);
    }

    private void getVolume() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(TEASPOON, R.string.teaspoon, R.drawable.ic_volume, 0.0000049289215938, 202884.136211058));
        units.add(new Unit(TABLESPOON, R.string.tablespoon, R.drawable.ic_volume, 0.0000147867647812, 67628.045403686));
        units.add(new Unit(CUP, R.string.cup, R.drawable.ic_volume, 0.0002365882365, 4226.7528377304));
        units.add(new Unit(FLUID_OUNCE, R.string.fluid_ounce, R.drawable.ic_volume, 0.0000295735295625, 33814.0227018429972));
        units.add(new Unit(FLUID_OUNCE_UK, R.string.fluid_ounce_uk, R.drawable.ic_volume, 0.0000284130625, 35195.07972785404600437));
        units.add(new Unit(PINT, R.string.pint, R.drawable.ic_volume, 0.000473176473, 2113.37641886518732));
        units.add(new Unit(PINT_UK, R.string.pint_uk, R.drawable.ic_volume, 0.00056826125, 1759.753986392702300218));
        units.add(new Unit(QUART, R.string.quart, R.drawable.ic_volume, 0.000946352946, 1056.68820943259366));
        units.add(new Unit(QUART_UK, R.string.quart_uk, R.drawable.ic_volume, 0.0011365225, 879.8769931963511501092));
        units.add(new Unit(GALLON, R.string.gallon, R.drawable.ic_volume, 0.003785411784, 264.172052358148415));
        units.add(new Unit(GALLON_UK, R.string.gallon_uk, R.drawable.ic_volume, 0.00454609, 219.9692482990877875273));
        units.add(new Unit(BARREL, R.string.barrel, R.drawable.ic_volume, 0.119240471196, 8.38641436057614017079));
        units.add(new Unit(BARREL_UK, R.string.barrel_uk, R.drawable.ic_volume, 0.16365924, 6.11025689719688298687));
        units.add(new Unit(MILLILITRE, R.string.millilitre, R.drawable.ic_volume, 0.000001, 1000000.0));
        units.add(new Unit(LITRE, R.string.litre, R.drawable.ic_volume, 0.001, 1000.0));
        units.add(new Unit(CUBIC_CM, R.string.cubic_cm, R.drawable.ic_volume, 0.000001, 1000000.0));
        units.add(new Unit(CUBIC_M, R.string.cubic_m, R.drawable.ic_volume, 1.0, 1.0));
        units.add(new Unit(CUBIC_INCH, R.string.cubic_inch, R.drawable.ic_volume, 0.000016387064, 61023.744094732284));
        units.add(new Unit(CUBIC_FOOT, R.string.cubic_foot, R.drawable.ic_volume, 0.028316846592, 35.3146667214885903));
        units.add(new Unit(CUBIC_YARD, R.string.cubic_yard, R.drawable.ic_volume, 0.7645548692741148, 1.3079506));
        map.put(VOLUME, units);
    }

    private void getWeight() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(KILOGRAM, R.string.kilogram, R.drawable.ic_weight, 1.0, 1.0));
        units.add(new Unit(POUND, R.string.pound, R.drawable.ic_weight, 0.45359237, 2.20462262184877581));
        units.add(new Unit(GRAM, R.string.gram, R.drawable.ic_weight, 0.001, 1000.0));
        units.add(new Unit(MILLIGRAM, R.string.milligram, R.drawable.ic_weight, 0.000001, 1000000.0));
        units.add(new Unit(OUNCE, R.string.ounce, R.drawable.ic_weight, 0.028349523125, 35.27396194958041291568));
        units.add(new Unit(GRAIN, R.string.grain, R.drawable.ic_weight, 0.00006479891, 15432.35835294143065061));
        units.add(new Unit(STONE, R.string.stone, R.drawable.ic_weight, 6.35029318, 0.15747304441777));
        units.add(new Unit(METRIC_TON, R.string.metric_ton, R.drawable.ic_weight, 1000.0, 0.001));
        units.add(new Unit(SHORT_TON, R.string.short_ton, R.drawable.ic_weight, 907.18474, 0.0011023113109243879));
        units.add(new Unit(LONG_TON, R.string.long_ton, R.drawable.ic_weight, 1016.0469088, 0.0009842065276110606282276));
        map.put(WEIGHT, units);
    }

    private void getLength() {
        List<Unit> units = new ArrayList<>();
        units.add(new Unit(KILOMETRE, R.string.kilometre, R.drawable.ic_length, 1000.0, 0.001));
        units.add(new Unit(MILE, R.string.mile, R.drawable.ic_length, 1609.344, 0.00062137119223733397));
        units.add(new Unit(METRE, R.string.metre, R.drawable.ic_length, 1.0, 1.0));
        units.add(new Unit(CENTIMETRE, R.string.centimetre, R.drawable.ic_length, 0.01, 100.0));
        units.add(new Unit(MILLIMETRE, R.string.millimetre, R.drawable.ic_length, 0.001, 1000.0));
        units.add(new Unit(MICROMETRE, R.string.micrometre, R.drawable.ic_length, 0.000001, 1000000.0));
        units.add(new Unit(NANOMETRE, R.string.nanometre, R.drawable.ic_length, 0.000000001, 1000000000.0));
        units.add(new Unit(YARD, R.string.yard, R.drawable.ic_length, 0.9144, 1.09361329833770779));
        units.add(new Unit(FEET, R.string.feet, R.drawable.ic_length, 0.3048, 3.28083989501312336));
        units.add(new Unit(INCH, R.string.inch, R.drawable.ic_length, 0.0254, 39.3700787401574803));
        units.add(new Unit(NAUTICAL_MILE, R.string.nautical_mile, R.drawable.ic_length, 1852.0, 0.000539956803455723542));
        units.add(new Unit(FURLONG, R.string.furlong, R.drawable.ic_length, 201.168, 0.0049709695379));
        units.add(new Unit(LIGHT_YEAR, R.string.light_year, R.drawable.ic_length, 9460730472580800.0, 0.0000000000000001057000834024615463709));
        map.put(LENGTH, units);
    }
}
