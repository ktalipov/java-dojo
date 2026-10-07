public class BooleanDataType {
    public static void main(String[] args) {

        // boolean can store only two values:
        // true or false.
        boolean isJavaFun = true;
        boolean hasError = false;

        System.out.println("isJavaFun: " + isJavaFun);
        System.out.println("hasError: " + hasError);


        // Comparison operators return a boolean value.
        int age = 23;

        boolean isAdult = age >= 18;
        boolean isMinor = age < 18;

        System.out.println("Is adult: " + isAdult);
        System.out.println("Is minor: " + isMinor);


        // Logical AND (&&) returns true only when
        // both conditions are true.
        boolean hasTicket = true;
        boolean canEnter = isAdult && hasTicket;

        System.out.println("Can enter: " + canEnter);


        // Logical OR (||) returns true when
        // at least one condition is true.
        boolean isStudent = true;
        boolean hasDiscount = isStudent || age < 18;

        System.out.println("Has discount: " + hasDiscount);


        // Logical NOT (!) reverses a boolean value.
        boolean isLoggedIn = false;

        System.out.println("Is logged in: " + isLoggedIn);
        System.out.println("Is not logged in: " + !isLoggedIn);


        // Logical XOR (^) returns true when
        // the two boolean values are different.
        boolean first = true;
        boolean second = false;

        System.out.println("XOR result: " + (first ^ second));


        // boolean values can be used directly in if statements.
        if (isAdult) {
            System.out.println("The person is an adult.");
        }


        // A boolean method can return the result of a condition.
        System.out.println("Is 10 greater than 5: "
                + isGreaterThan(10, 5));


        // The ternary operator can choose between two values
        // based on a boolean condition.
        String status = isLoggedIn ? "Online" : "Offline";

        System.out.println("Status: " + status);
    }


    // A method can return a boolean value.
    private static boolean isGreaterThan(int a, int b) {
        return a > b;
    }
}
