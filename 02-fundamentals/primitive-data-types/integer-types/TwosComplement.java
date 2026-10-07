public class TwosComplement {
    public static void main(String[] args) {

        // A byte uses 8 bits.
        // The leftmost bit is the sign bit:
        // 0 means positive
        // 1 means negative

        byte positive = 5;

        // Binary representation of 5:
        // 00000101


        // Negative numbers are represented using two's complement.
        byte negative = -5;

        // Binary representation of -5:
        // 11111011


        // To calculate -5 using two's complement:
        //
        // Step 1: Write 5 in binary
        // 00000101
        //
        // Step 2: Invert all bits
        // 11111010
        //
        // Step 3: Add 1
        // 11111011
        //
        // Therefore:
        // 11111011 represents -5.


        // Two's complement also explains integer overflow.
        //
        // The maximum value of byte is 127:
        // 01111111
        //
        // Adding 1:
        // 10000000
        //
        // 10000000 represents -128.

        byte maxValue = 127;
        byte overflow = (byte) (maxValue + 1);

        System.out.println("Maximum byte value: " + maxValue);
        System.out.println("After overflow: " + overflow);
    }
}
