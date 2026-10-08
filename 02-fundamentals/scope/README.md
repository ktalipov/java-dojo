# Variable Scope and Lifetime in Java

Reference documentation on variable visibility rules and lifetime, based on the Java Syntax course materials.

---

## 1. Core Concepts

* **Variable Scope** — the region of a program (code blocks, methods, classes, packages) from which a variable, method, or class can be accessed.
* **Lifetime** — the period of program execution during which a variable exists in memory, holds its value, and can be used.
* **Encapsulation** — properly restricting scope protects data from unauthorized access and accidental modification by outside parts of the program.

---

## 2. Local Variables and Code Blocks

Local variables are declared inside a method or inside a block delimited by curly braces `{}`.

### 2.1. Scope Rules for Local Variables

1. **Start of visibility**: a variable is accessible strictly **after** the line where it is declared. Using a variable before its declaration causes a compile-time error.
2. **Block boundaries**: a variable is visible and exists only until the closing brace `}` of the block in which it was declared.
3. **Method parameters**: parameters are available throughout the entire body of their method.
4. **Loop variables**: a variable declared in a loop header (e.g. `for (int i = 0; ...)`) is accessible only inside the loop body and is destroyed as soon as the loop ends.

### 2.2. Lifetime and Initialization

* A variable is created when execution enters its scope and is **destroyed when execution leaves** it.
* Each time a block is re-entered (e.g. on every loop iteration), the local variable is initialized anew. Any value assigned during the previous iteration is lost.

```java
public class LifeTimeDemo {
    public static void main(String[] args) {
        for (int x = 0; x < 2; x++) {
            int y = -1; // y is re-initialized on every entry into the loop body
            System.out.println("y = " + y); // prints -1
            y = 100;
            System.out.println("y = " + y); // prints 100
        }
    }
}
```

### 2.3. Nested Blocks and Naming

* **Nesting**: code in an inner block can access variables declared in the enclosing (outer) block. The reverse is not true: outer code cannot see variables of an inner block.
* **No redeclaration**: Java does not allow declaring a local variable in an inner block with the same name as a variable in the enclosing block of the same method (compile-time error).
* **Non-overlapping blocks**: sequential, non-overlapping blocks may declare variables with the same name.

---

## 3. Class Fields (Instance and Static Scope)

Variables declared inside a class but outside any method are called fields (the state of the class).

### 3.1. Instance Variables

* Declared without the `static` modifier.
* Belong to a specific object (an instance of the class).
* **Lifetime**: exist for as long as the object exists.
* **Scope**: accessible in constructors and all non-static methods of the class.

### 3.2. Static Variables

* Declared with the `static` keyword.
* Belong to the class itself, not to individual objects. Accessible without creating an instance (via `ClassName.variable`).
* **Lifetime**: exist in memory for the entire duration of the program.
* **Scope**: visible in all methods of the class, both static and non-static.

---

## 4. Access Modifiers

Access modifiers control the visibility of classes, fields, and methods from other classes and packages.

| Modifier | Same class | Same package | Subclasses (even in another package) | Any class |
| :--- | :---: | :---: | :---: | :---: |
| `private` | **Yes** | No | No | No |
| *(no modifier)* / `package-private` | **Yes** | **Yes** | No | No |
| `protected` | **Yes** | **Yes** | **Yes** | No |
| `public` | **Yes** | **Yes** | **Yes** | **Yes** |

### Notes on Each Modifier

* `private`: the most restrictive level. Fields and methods are visible only inside their own class. Cannot be applied to top-level classes.
* **Default (`package-private`)**: applied when no modifier is specified. Members are visible to all classes in the same package (directory).
* `protected`: grants access within the same package, as well as to subclasses in other packages.
* `public`: maximum openness. Accessible from anywhere in the program.

---

## 5. Variable Shadowing and `this`

If a method parameter or local variable has the same name as a class field, the local variable **shadows** the field within that method.

To refer explicitly to the class field in such cases, use the `this` keyword:

```java
public class Car {
    private int consumption; // class field

    public Car(int consumption) { // constructor parameter with the same name
        this.consumption = consumption; // this.consumption refers to the field
    }
}
```

---

## 6. Common Compilation Errors

1. **Using a variable before its declaration**:
   ```java
   count = 10; // Error!
   int count;
   ```
2. **Accessing a variable outside its block**:
   ```java
   if (condition) {
       int change = 50;
   }
   System.out.println(change); // Error: cannot find symbol (variable change)
   ```
3. **Redeclaring a variable in a nested block**:
   ```java
   int bar = 1;
   {
       int bar = 2; // Error: variable bar is already defined
   }
   ```
