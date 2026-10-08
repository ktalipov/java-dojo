public class ScopeAndLifetime {

    // Instance variable.
    // Belongs to an object of this class.
    private int instanceVariable = 10;

    // Static variable.
    // Belongs to the class itself.
    private static int staticVariable = 20;


    public static void main(String[] args) {

        // Local variable.
        // Accessible only inside the main method.
        int localVariable = 30;

        System.out.println("Local variable: " + localVariable);


        // A local variable is accessible after its declaration.
        int number = 100;

        System.out.println("Number: " + number);


        // Nested blocks create their own scope.
        int outerVariable = 10;

        {
            int innerVariable = 20;

            // The inner block can access variables
            // from the outer block.
            System.out.println("Outer variable: " + outerVariable);
            System.out.println("Inner variable: " + innerVariable);
        }

        // The outer variable is still accessible.
        System.out.println("Outer variable: " + outerVariable);

        // The inner variable is no longer accessible.
        // System.out.println(innerVariable); // Compile-time error


        // A variable declared in a loop header
        // is accessible only inside the loop.
        for (int i = 0; i < 2; i++) {

            // This variable is initialized every time
            // the loop body is entered.
            int value = -1;

            System.out.println("Initial value: " + value);

            value = 100;

            System.out.println("Changed value: " + value);
        }

        // i and value are not accessible here.
        // System.out.println(i);     // Compile-time error
        // System.out.println(value); // Compile-time error


        // Static variables belong to the class.
        System.out.println("Static variable: " + staticVariable);
    }


    // Method parameters are local variables.
    // They are accessible throughout the method body.
    private static void printNumber(int number) {
        System.out.println("Parameter: " + number);
    }
}
