public class TypeConversionAndCasting {

  public static void main(String[] args) {

    // 1. Automatic Type Conversion (Widening)
    System.out.println("=== 1. Automatic Type Conversion ===");

    int intValue = 100;
    long longValue = intValue;
    double doubleValue = longValue;

    System.out.println("int value: " + intValue);
    System.out.println("long value: " + longValue);
    System.out.println("double value: " + doubleValue);

    // 2. Explicit Type Casting (Narrowing)
    System.out.println("\n=== 2. Explicit Type Casting ===");

    int largeValue = 257;
    byte byteValue = (byte) largeValue;

    System.out.println("Original int: " + largeValue);
    System.out.println("Casted byte: " + byteValue);

    // The value wraps around because byte has 256 possible values.

    // 3. Floating-Point to Integer Conversion
    System.out.println("\n=== 3. Floating-Point Conversion ===");

    double decimalValue = 323.142;
    int convertedInt = (int) decimalValue;
    byte convertedByte = (byte) decimalValue;

    System.out.println("Original double: " + decimalValue);
    System.out.println("Casted int: " + convertedInt);
    System.out.println("Casted byte: " + convertedByte);

    // The fractional part is discarded before narrowing to byte.

    // 4. Automatic Type Promotion
    System.out.println("\n=== 4. Automatic Type Promotion ===");

    byte b = 50;
    int result = b * 2;

    System.out.println("byte value: " + b);
    System.out.println("Result of b * 2: " + result);

    // byte operands are promoted to int during arithmetic.

    byte castedResult = (byte) (b * 2);
    System.out.println("Result cast back to byte: " + castedResult);

    // 5. Binary Numeric Promotion
    System.out.println("\n=== 5. Binary Numeric Promotion ===");

    byte smallNumber = 42;
    char character = 'a';
    short shortNumber = 1024;
    int integerNumber = 50000;
    float floatNumber = 5.67f;
    double doubleNumber = 0.1234;

    double calculation = (floatNumber * smallNumber)
        + (integerNumber / character)
        - (doubleNumber * shortNumber);

    System.out.println("Character: " + character);
    System.out.println("Character numeric value: " + (int) character);
    System.out.println("Calculation result: " + calculation);

    // 6. Literal Assignment
    System.out.println("\n=== 6. Literal Assignment ===");

    byte byteLiteral = 100;
    short shortLiteral = 1000;
    char charLiteral = 65;

    System.out.println("byte literal: " + byteLiteral);
    System.out.println("short literal: " + shortLiteral);
    System.out.println("char literal: " + charLiteral);

    // Constant int literals can be assigned to byte, short,
    // or char when their values fit the destination type.
  }
}
