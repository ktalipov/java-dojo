public class FloatingPointDataTypes {
    public static void main(String[] args) {

        // float is a 32-bit floating-point type.
        // It provides approximately 7 decimal digits of precision.
        // The suffix 'f' is required when assigning a floating-point
        // literal to a float variable.
        float floatNumber = 3.14f;

        // double is a 64-bit floating-point type.
        // It provides approximately 15 decimal digits of precision.
        // Floating-point literals are double by default.
        double doubleNumber = 3.14;

        System.out.println("float:  " + floatNumber);
        System.out.println("double: " + doubleNumber);


        // Scientific notation can be used to represent
        // very large or very small numbers.
        double scientificNumber = 1.5e3;

        // 1.5 × 10^3 = 1500.0
        System.out.println("Scientific notation: " + scientificNumber);


        // Underscores can be used inside numeric literals
        // to improve readability.
        double largeNumber = 1_000_000.5;

        System.out.println("Large number: " + largeNumber);


        // Floating-point arithmetic can produce rounding errors.
        // This happens because many decimal fractions cannot be
        // represented exactly in binary.
        double result = 0.1 + 0.2;

        System.out.println("0.1 + 0.2 = " + result);
        System.out.println("Is result equal to 0.3? " + (result == 0.3));


        // The difference between the calculated value and the
        // expected value can be checked using a tolerance.
        double tolerance = 1e-9;

        System.out.println(
                "Is result close to 0.3? "
                        + (Math.abs(result - 0.3) < tolerance)
        );


        // Floating-point types can represent special values.
        // Dividing a positive floating-point number by zero
        // produces positive infinity.
        double infinity = 1.0 / 0.0;

        System.out.println("Infinity: " + infinity);


        // 0.0 divided by 0.0 produces NaN (Not a Number).
        double nan = 0.0 / 0.0;

        System.out.println("NaN: " + nan);
        System.out.println("Is NaN? " + Double.isNaN(nan));


        // MIN_VALUE is the smallest positive non-zero value,
        // not the most negative value.
        System.out.println("Float.MIN_VALUE: " + Float.MIN_VALUE);
        System.out.println("Float.MAX_VALUE: " + Float.MAX_VALUE);

        System.out.println("Double.MIN_VALUE: " + Double.MIN_VALUE);
        System.out.println("Double.MAX_VALUE: " + Double.MAX_VALUE);
    }
}
