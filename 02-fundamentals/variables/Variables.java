// Demonstrating Variavles in Java
class Variables {

    // Instance variable: declared inside the class but outside any method.
    // Each object of the class has its own copy of this variable.
    int instanceVariable = 10;

    // Class variable (static variable): belongs to the class rather than
    // to a specific object. All objects share the same variable.
    static int classVariable = 20;

    // Constant: the final keyword prevents the value from being changed
    // after it has been initialized.
    // Constants are conventionally written in UPPERCASE.
    static final int MAX_VALUE = 100;

    public static void main(String[] args) {

        // Variable declaration: the variable x is declared as an int,
        // but no value has been assigned to it yet.
        int x;

        // Assignment: the value 9 is assigned to the previously declared x.
        x = 9;

        System.out.println("x = " + x);

        // Declaration and initialization:
        // the variable y is declared and assigned a value at the same time.
        int y = 7;

        System.out.println("y = " + y);

        // Reassignment: the previous value of x is replaced with 4.
        x = 4;

        System.out.println("x after reassignment = " + x);

        // Assigning the value of another variable:
        // the current value of y is copied into x.
        x = y;

        System.out.println("x after assigning y = " + x);

        // Assignment using an expression:
        // y + 5 is calculated first, and the result is assigned to x.
        x = y + 5;

        System.out.println("x after y + 5 = " + x);

        // The current value of x is increased by 6.
        // This is equivalent to: x = x + 6;
        x = x + 6;

        System.out.println("x after x + 6 = " + x);

        // Compound assignment:
        // x += 6 is a shorter form of x = x + 6.
        x += 6;

        System.out.println("x after x += 6 = " + x);

        // Local variable:
        // a variable declared inside a method is a local variable.
        // Local variables must be initialized before they are read.
        int localVariable = 50;

        System.out.println("localVariable = " + localVariable);

        // Local variable type inference:
        // the compiler infers the type of value from the initializer.
        // In this case, value is inferred as int.
        var value = 9;

        System.out.println("value = " + value);

        // The type of a variable declared with var cannot be changed.
        // value = "Java"; // Compilation error because value is an int.

        // Creating an object of the Variables class.
        // This object is used to access the instance variable.
        Variables object = new Variables();

        // Accessing an instance variable through an object reference.
        System.out.println(
                "instanceVariable = " + object.instanceVariable
        );

        // Accessing a class (static) variable through the class name.
        System.out.println(
                "classVariable = " + Variables.classVariable
        );

        // Accessing a constant.
        System.out.println(
                "MAX_VALUE = " + Variables.MAX_VALUE
        );

        // MAX_VALUE cannot be reassigned because it is final.
        // Variables.MAX_VALUE = 200; // Compilation error.
    }
}
