
// Demonstrating Primitive Data Types in Java
class PrimitiveDataTypes {
    public static void main(String[] args) {

        // byte is an 8-bit signed integer type.
        // Its range is from -128 to 127.
        byte bt = 57;

        // short is a 16-bit signed integer type.
        // Its range is from -32,768 to 32,767.
        short sh = 23_944;

        // int is a 32-bit signed integer type.
        // It is the default type for integer literals.
        int i = 67_054_444;

        // long is a 64-bit signed integer type.
        // The 'L' suffix tells the compiler that this literal is a long.
        // Underscores are allowed in numeric literals to improve readability.
        long l = 203_000_500_344_323L;

        // double is a 64-bit floating-point type.
        // Decimal literals are double by default.
        double d = 267.099;

        // float is a 32-bit floating-point type.
        // The 'f' suffix tells the compiler that this literal is a float.
        float f = 89.04f;

        // char is a 16-bit type used to represent a single character.
        // A char can also be initialized using its Unicode value.
        // Unicode value 88 represents the character 'X'.
        char ch1 = 88;

        // A char can also be initialized directly with a character.
        // Character literals are written using single quotes.
        char ch2 = 'Y';

        // boolean represents a logical value.
        // It can contain only true or false.
        boolean b = true;

        // Print the value stored in the byte variable.
        System.out.println("byte: " + bt);

        // Print the value stored in the short variable.
        System.out.println("short: " + sh);

        // Print the value stored in the int variable.
        System.out.println("int: " + i);

        // Print the value stored in the long variable.
        System.out.println("long: " + l);

        // Print the value stored in the double variable.
        System.out.println("double: " + d);

        // Print the value stored in the float variable.
        System.out.println("float: " + f);

        // Print the character represented by the Unicode value 88.
        // The output will be 'X'.
        System.out.println("char from Unicode value 88: " + ch1);

        // Print the character stored directly in ch2.
        System.out.println("char: " + ch2);

        // Print the boolean value.
        System.out.println("boolean: " + b);
    }
}
