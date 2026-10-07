public class IntegerDataTypes {

    public static void main(String[] args) {

        // byte is an 8-bit signed integer type.
        // Range: -128 to 127.
        // It is useful when a small range of integer values is sufficient.
        byte age = 23;

        // short is a 16-bit signed integer type.
        // Range: -32,768 to 32,767.
        short year = 2026;

        // int is a 32-bit signed integer type.
        // Range: -2,147,483,648 to 2,147,483,647.
        // It is the most commonly used integer type in Java.
        int population = 7_000_000;

        // long is a 64-bit signed integer type.
        // Range: -9,223,372,036,854,775,808
        //        to 9,223,372,036,854,775,807.
        // The suffix L indicates that the literal is of type long.
        long distance = 9_000_000_000L;


        // Print the values of the variables.
        System.out.println("byte:  " + age);
        System.out.println("short: " + year);
        System.out.println("int:   " + population);
        System.out.println("long:  " + distance);


        // Underscores can be used in numeric literals
        // to improve the readability of large numbers.
        int oneMillion = 1_000_000;
        long largeNumber = 10_000_000_000L;

        System.out.println("oneMillion:  " + oneMillion);
        System.out.println("largeNumber: " + largeNumber);


        // Integer types can store negative values.
        int negativeNumber = -100;
        byte zero = 0;

        System.out.println("negativeNumber: " + negativeNumber);
        System.out.println("zero: " + zero);


        // Integer overflow occurs when the result exceeds
        // the maximum value that can be represented by the type.
        byte maxByte = 127;
        byte overflow = (byte) (maxByte + 1);

        System.out.println("byte overflow: " + overflow);
        // Output: -128


        // Integer literals are of type int by default.
        int number = 100;

        // The suffix L is used to explicitly specify a long literal.
        long longNumber = 100L;

        System.out.println("int literal:  " + number);
        System.out.println("long literal: " + longNumber);
    }
}
