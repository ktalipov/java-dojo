public class CharacterDataType {
    public static void main(String[] args) {

        // char is a 16-bit unsigned integral type.
        // Its range is 0 to 65,535.
        char letter = 'A';

        System.out.println("Character: " + letter);


        // A char can also be initialized using a Unicode escape.
        // U+0410 is the Unicode code point for the Cyrillic letter 'А'.
        char cyrillicLetter = '\u0410';

        System.out.println("Unicode character: " + cyrillicLetter);


        // A char can be initialized using an integer value
        // from 0 to 65,535.
        // The value 65 represents the character 'A'.
        char characterCode = 65;

        System.out.println("Character from code: " + characterCode);


        // Common escape sequences can be used with char.
        char tab = '\t';
        char newline = '\n';
        char singleQuote = '\'';
        char backslash = '\\';

        System.out.println("Tab:" + tab + "End");
        System.out.println("Line 1" + newline + "Line 2");
        System.out.println("Single quote: " + singleQuote);
        System.out.println("Backslash: " + backslash);


        // char is an integral type, so arithmetic operations
        // can be performed on char values.
        char a = 'a';

        // char is promoted to int during arithmetic.
        System.out.println("a + 1: " + (a + 1));

        // To store the result back in a char,
        // an explicit cast is required.
        char nextLetter = (char) (a + 1);

        System.out.println("Next letter: " + nextLetter);


        // The increment operator can be used directly with char.
        char letterToIncrement = 'a';

        letterToIncrement++;

        System.out.println("After increment: " + letterToIncrement);


        // A digit character is not the same as its numeric value.
        // The character '7' has the Unicode value 55.
        char digit = '7';

        System.out.println("Character: " + digit);
        System.out.println("Character code: " + (int) digit);


        // Subtracting '0' converts an ASCII digit character
        // into its numeric value.
        int numericValue = digit - '0';

        System.out.println("Numeric value: " + numericValue);


        // Character provides useful methods for working with Unicode.
        char letterToCheck = 'Я';

        System.out.println("Is letter: "
                + Character.isLetter(letterToCheck));

        System.out.println("Is uppercase: "
                + Character.isUpperCase(letterToCheck));

        System.out.println("Lowercase: "
                + Character.toLowerCase(letterToCheck));


        // A char represents one UTF-16 code unit.
        // Characters outside the Basic Multilingual Plane (BMP)
        // require two char values.
        //
        // The emoji 😀 has the Unicode code point U+1F600.
        String emoji = "😀";

        System.out.println("Emoji: " + emoji);

        // String.length() counts UTF-16 code units.
        // The emoji therefore takes 2 char values.
        System.out.println("String length: " + emoji.length());

        // codePointCount() counts Unicode code points.
        System.out.println(
                "Code point count: "
                        + emoji.codePointCount(0, emoji.length())
        );
    }
}
