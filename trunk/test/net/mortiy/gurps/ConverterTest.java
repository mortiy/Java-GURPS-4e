package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.utils.Converter;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 09.12.12
 * Time: 19:22
 */
public class ConverterTest extends TestCase {
    public void testConverter() throws Exception {
        Converter converter = Converter.getInstance();

        assertEquals("How many yards in 25 meters",
                converter.roundTo(25.0f / Converter.METERS_IN_YARD, 3),
                converter.convert(25, Converter.Units.Meter, Converter.Units.Yard)
        );

        assertEquals("How many foots in 2 meters",
                converter.roundTo(200f / Converter.CENTIMETERS_IN_FOOT, 3),
                converter.convert(2, Converter.Units.Meter, Converter.Units.Foot)
        );

        assertEquals("How many litres in 10 gallons",
                converter.roundTo(10 * Converter.LITERS_IN_GALLON, 3),
                converter.convert(10, Converter.Units.Gallon, Converter.Units.Liter)
        );

        assertEquals("How many meters in 20 inches",
                converter.roundTo(0.508f, 3),
                converter.convert(20, Converter.Units.Inch, Converter.Units.Meter)
        );

        try {
            assertEquals("How many meters in 20 inches - parsing test",
                    converter.roundTo(20 * Converter.CENTIMETERS_IN_INCH / 100f, 3),
                    converter.convert("20\"", Converter.Units.Meter)
            );
            assertEquals("How many centimeters in 6 feets 9 inches - parsing test",
                    converter.roundTo((6 * Converter.INCHES_IN_FOOT + 9) * Converter.CENTIMETERS_IN_INCH, 3),
                    converter.convert("6'9\"", Converter.Units.Centimeter)
            );
        } catch (Converter.WrongExpressionException e) {
            throw e;
        }
    }

    public void testTimeConverter() throws Exception {
        Converter converter = Converter.getInstance();

        assertEquals("How many miles in 100 yards?", 0.057f, converter.convert(new Converter.Value(100, Converter.Units.Yard), Converter.Units.Mile));
        assertEquals("How many seconds in 5 minutes?", 300f, converter.convert(new Converter.Value(5, Converter.Units.Minute), Converter.Units.Second));
        assertEquals("How many days in 6 hours?", 0.25f, converter.convert(new Converter.Value(6, Converter.Units.Hour), Converter.Units.Day));
        assertEquals("How many minutes in 10 seconds?", 0.167f, converter.convert(new Converter.Value(10, Converter.Units.Second), Converter.Units.Minute));
        assertEquals("How many hours in 5 days", 120f, converter.convert(new Converter.Value(5, Converter.Units.Day), Converter.Units.Hour));
    }
}
