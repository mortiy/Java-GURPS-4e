package net.mortiy.gurps.rules.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used to convert Imperial units to Metric and vise verse.
 */
public class Converter {

    private static Converter instance = new Converter();

    final int DEFAULT_DECIMAL_PLACES = 3;

    final public static float CENTIMETERS_IN_INCH = 2.54f;
    final public static float CENTIMETERS_IN_FOOT = 30.48f;
    final public static float METERS_IN_YARD = 0.9144f;
    final public static float KILOMETERS_IN_MILE = 1.609f;
    final public static float KILOGRAMS_IN_POUND = 0.454f;
    final public static float LITERS_IN_GALLON = 3.785411f;
    final public static float LITERS_IN_QUART = 0.9463529f;
    final public static float GRAMS_IN_OUNCE = 28.349f;

    final public static int OUNCE_IN_POUND = 16;

    final public static int QUARTS_IN_GALLON = 4;
    final public static int YARDS_IN_MILE = 1760;

    final public static int FOOTS_IN_YARD = 3;
    final public static int FOOTS_IN_MILE = 5280;

    final public static int INCHES_IN_FOOT = 12;
    final public static int INCHES_IN_YARD = INCHES_IN_FOOT * FOOTS_IN_YARD;
    final public static int INCHES_IN_MILE = INCHES_IN_YARD * YARDS_IN_MILE;

    final private static int SECONDS_IN_MINUTE = 60;
    final private static int SECONDS_IN_HOUR = 60 * SECONDS_IN_MINUTE;
    final private static int SECONDS_IN_DAY = 24 * SECONDS_IN_HOUR;
    final private static int SECONDS_IN_MONTH = 30 * SECONDS_IN_DAY;

    /**
     * Conversion column:
     * 1 inch (in.)         2.5 cm          2.54 cm
     * 1 foot (ft.)         30 cm           30.48 cm
     * 1 yard (yd.)         1 meter         0.914 meters
     * 1 mile (mi.)         1.5 km          1.609 km
     * 1 pound (lb.)        0.5 kg          0.454 kg
     * 1 ton                1 metric ton    0.907 metric tons
     * 1 gallon (gal.)      4 liters        3.785 liters
     * 1 quart (qt.)        1 liter         0.946 liters
     * 1 ounce (oz.)        30 grams        28.349 grams
     * 1 cubic inch (ci)    16 cubic cm     16.387 cu. cm
     * 1 cubic yard (cy)    0.75 cubic m    0.765 cubic m
     */
    public static enum Units {
        Unknown,
        // Imperial
        Inch,
        Foot,
        Yard,
        Mile,
        Pound,
        Ton,
        Gallon,
        Quart,
        Ounce,
        CubicInch,
        CubicYard,
        FahrenheitDegree,

        // Metric
        Centimeter,
        Meter,
        Kilometer,
        Kilogram,
        Liter,
        MetricTon,
        Gram,
        CubicCentimeter,
        CubicMeter,
        CelsiusDegree,

        Month,
        Day,
        Hour,
        Minute,
        Second
    }

    public static class Value {
        public Units unit;
        public float value;

        public Value(float value, Units unit) {
            this.unit = unit;
            this.value = value;
        }
    }

    private Converter() {
    }

    public static Converter getInstance() {
        return instance;
    }

    public Value parse(String expression) throws WrongExpressionException {
        Units inputUnits = Units.Unknown;
        float value = 0f;
        if (expression.contains("\"") || expression.contains("\'")) {
            float inches = 0f;
            inputUnits = Units.Inch;
            Pattern pattern = Pattern.compile("(?:(\\d+)\')?(?:(\\d+)\")?");
            Matcher matcher = pattern.matcher(expression);
            if (!matcher.matches()) {
                throw new WrongExpressionException();
            }
            try {
                inches += matcher.group(1) != null ? Integer.parseInt(matcher.group(1)) * INCHES_IN_FOOT : 0;
                inches += matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 0;
            } catch (NumberFormatException e) {
                throw new WrongExpressionException(e);
            }
            value = inches;
        }
        return new Value(value, inputUnits);
    }

    public float convert(Value value) {
        return convert(value, Units.Unknown);
    }

    public float convert(Value value, Units outputUnits) {
        return convert(value.value, value.unit, outputUnits);
    }

    public float convert(float value, Units inputUnits) {
        return convert(value, inputUnits, Units.Unknown, DEFAULT_DECIMAL_PLACES);
    }

    public float convert(float value, Units inputUnits, Units outputUnits) {
        return convert(value, inputUnits, outputUnits, DEFAULT_DECIMAL_PLACES);
    }

    public float convert(float value, Units inputUnits, Units outputUnits, int decimalPlaces) {
        if (inputUnits == outputUnits) {
            return value;
        }
        int firstMetricUnitOrdinal = Units.Centimeter.ordinal();
        if (inputUnits.ordinal() >= firstMetricUnitOrdinal) {
            if (outputUnits.ordinal() >= firstMetricUnitOrdinal) {
                return metricToMetric(value, inputUnits, outputUnits, decimalPlaces);
            } else {
                return toImperial(value, inputUnits, outputUnits, decimalPlaces);
            }

        } else {
            if (outputUnits.ordinal() >= firstMetricUnitOrdinal) {
                return toMetric(value, inputUnits, outputUnits, decimalPlaces);
            } else {
                return impericToImperic(value, inputUnits, outputUnits, decimalPlaces);
            }
        }
    }

    public float convert(String expression, Units outputUnits) throws WrongExpressionException {
        return convert(expression, outputUnits, DEFAULT_DECIMAL_PLACES);
    }

    public float convert(String expression, Units outputUnits, int decimalPlaces) throws WrongExpressionException {

        Value parsedValue = parse(expression);
        float value = parsedValue.value;
        Units inputUnits = parsedValue.unit;

        return convert(value, inputUnits, outputUnits, decimalPlaces);
    }

    private float toImperial(float value, Units inputUnits) {
        return toImperial(value, inputUnits, Units.Unknown, DEFAULT_DECIMAL_PLACES);
    }

    private float toImperial(float value, Units inputUnits, Units outputUnits) {
        return toImperial(value, inputUnits, outputUnits, DEFAULT_DECIMAL_PLACES);
    }

    /**
     * Return imperial representation of metric value
     *
     * @param value         Number
     * @param inputUnits    Metric input unit
     * @param outputUnits   Imperial output unit
     * @param decimalPlaces N decimal places output number rounded to.
     * @return Converted number
     */
    private float toImperial(float value, Units inputUnits, Units outputUnits, int decimalPlaces) {
        float output = 0f;
        switch (inputUnits) {
            case Liter:
                float quarts = value / LITERS_IN_QUART;
                switch (outputUnits) {
                    case Gallon:
                        output = quarts * QUARTS_IN_GALLON;
                        break;
                    default:
                        output = quarts;
                }
                break;
            case Gram:
                // Convert to kilograms:
                value /= 1000; // 1 kilogram = 1000 grams
            case Kilogram:
                float pounds = value / KILOGRAMS_IN_POUND;
                switch (outputUnits) {
                    case Ounce:
                        output = pounds * OUNCE_IN_POUND;
                        break;
                    default:
                        output = pounds;
                }

                break;
            case Kilometer:
                // Convert to meters
                value *= 1000; // 1 kilometer = 1000 meters
            case Meter:
                // Convert to centimeters:
                value *= 100; // 1 meters = 100 centimeters
            case Centimeter:
                float foots = value / CENTIMETERS_IN_FOOT;
                switch (outputUnits) {
                    case Mile:
                        output = foots / FOOTS_IN_MILE;
                        break;
                    case Yard:
                        output = foots / FOOTS_IN_YARD;
                        break;
                    default:
                        output = foots;
                }

                break;
        }
        return roundTo(output, decimalPlaces);
    }

    private float toMetric(float value, Units inputUnits) {
        return toMetric(value, inputUnits, Units.Unknown, DEFAULT_DECIMAL_PLACES);
    }

    private float toMetric(float value, Units inputUnits, Units outputUnits) {
        return toMetric(value, inputUnits, outputUnits, DEFAULT_DECIMAL_PLACES);
    }

    /**
     * Return metric representation of imperial value
     *
     * @param value         Number
     * @param inputUnits    Imperial input unit
     * @param outputUnits   Metric output unit
     * @param decimalPlaces N decimal places output number rounded to.
     * @return Converted number
     */
    private float toMetric(float value, Units inputUnits, Units outputUnits, int decimalPlaces) {
        double output = 0f;

        switch (inputUnits) {
            case Gallon:
                value *= QUARTS_IN_GALLON;
            case Quart:
                float litres = value * LITERS_IN_QUART;
                switch (outputUnits) {
                    default:
                        output = litres;
                }
                break;
            case Mile:
                value *= YARDS_IN_MILE;
            case Yard:
                value *= FOOTS_IN_YARD;
            case Foot:
                value *= INCHES_IN_FOOT;
            case Inch:
                float meters = (value * CENTIMETERS_IN_INCH) / 100;
                switch (outputUnits) {
                    case Centimeter:
                        output = meters * 100;
                        break;
                    case Kilometer:
                        output = meters / 1000;
                        break;
                    default:
                        output = meters;
                }

                break;

            case Ounce:
                // Convert to pounds:
                value /= OUNCE_IN_POUND; // 1 pound = 16 ounces
            case Pound:
                float kilograms = value * KILOGRAMS_IN_POUND;
                switch (outputUnits) {
                    case Gram:
                        output = kilograms * 1000;
                        break;
                    default:
                        output = kilograms;
                }

                break;

        }
        return roundTo((float) output, decimalPlaces);
    }


    private float metricToMetric(float value, Units inputUnits, Units outputUnits, int decimalPlaces) {
        if (inputUnits == outputUnits) {
            return value;
        }
        float output = 0f;
        switch (inputUnits) {
            case Month:
                value *= 30;
            case Day:
                value *= 24;
            case Hour:
                value *= 60;
            case Minute:
                value *= 60;
            case Second:
                switch (outputUnits){
                    case Month:
                        output = value / SECONDS_IN_MONTH;
                        break;
                    case Day:
                        output = value / SECONDS_IN_DAY;
                        break;
                    case Hour:
                        output = value / SECONDS_IN_HOUR;
                        break;
                    case Minute:
                        output = value / SECONDS_IN_MINUTE;
                        break;
                    default:
                        output = value;
                }
                break;

        }
        return roundTo(output, decimalPlaces);
    }

    private float impericToImperic(float value, Units inputUnits, Units outputUnits, int decimalPlaces) {
        if (inputUnits == outputUnits) {
            return value;
        }
        double output = 0.0f;
        switch (inputUnits) {
            case Mile:
                value *= YARDS_IN_MILE;
            case Yard:
                value *= FOOTS_IN_YARD;
            case Foot:
                value *= INCHES_IN_FOOT;
            case Inch:
                float inches = value;
                switch (outputUnits) {
                    case Mile:
                        output = inches / INCHES_IN_MILE;
                        break;
                    case Yard:
                        output = inches / INCHES_IN_YARD;
                        break;
                    case Foot:
                        output = inches / INCHES_IN_FOOT;
                        break;
                    default:
                        output = inches;
                }

                break;

        }
        return roundTo((float) output, decimalPlaces);
    }

    public float roundTo(float number, int decimalPlaces) {
        double multiplier = Math.pow(10, decimalPlaces);
        return (float) (Math.round(number * multiplier) / multiplier);
    }

    public class WrongExpressionException extends Exception {
        public WrongExpressionException() {
            super();    //To change body of overridden methods use File | Settings | File Templates.
        }

        public WrongExpressionException(Throwable throwable) {
            super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }
}
