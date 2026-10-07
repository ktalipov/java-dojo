# The `boolean` Type in Java

## 1. Overview

In Java, the keyword `boolean` denotes a primitive data type that can hold only one of two values: `true` or `false`. It is named after the mathematician George Boole and is the foundation for logical conditions, program state flags, and control flow.

---

## 2. Syntax and Variable Declaration

To declare a `boolean` variable, write the keyword `boolean`, the variable name, and optionally an initial value (the literal `true` or `false`).

```java
boolean isJavaFun = true;
boolean hasError = false;
```

### Key Properties

- **Allowed values:** strictly `true` or `false`. Assigning numbers (such as `1` or `0`) or values of other types is not possible and causes a compilation error. There are no casts between `boolean` and numeric types.
- **Default value:** class fields of type `boolean` are initialized to `false`. Local variables get no default value and must be assigned before use.

```java
// boolean flag = 1;        // compilation error: int cannot be converted to boolean
// int number = true;       // compilation error: boolean cannot be converted to int
```

---

## 3. Comparison Operators

Comparison operators evaluate the relationship between values and return a `boolean` result.

| Operator | Description      | Example      | Result  |
|:--------:|------------------|--------------|---------|
| `<`      | Less than        | `10 < 20`    | `true`  |
| `>`      | Greater than     | `10 > 9`     | `true`  |
| `<=`     | Less or equal    | `10 <= 10`   | `true`  |
| `>=`     | Greater or equal | `15 >= 20`   | `false` |
| `==`     | Equal            | `10 == 10`   | `true`  |
| `!=`     | Not equal        | `10 != 15`   | `true`  |

### Syntax Restrictions and Rules

1. **No spaces inside compound operators.** Two-character operators (`<=`, `>=`, `==`, `!=`) cannot be split by spaces. Writing `< =` or `! =` causes a compilation error.
2. **Character order.** Only `<=` and `>=` are valid. The forms `=<` and `=>` are not allowed.
3. **No chained comparisons.** An expression like `18 < age < 65` does not compile: `18 < age` evaluates to a `boolean`, which cannot be compared with the number `65`. Write `18 < age && age < 65` instead.

> **Reminder:** for objects (for example `String`), `==` compares references, not contents. Use `equals()` to compare contents.

---

## 4. Logical Operators

Logical operators combine or invert boolean expressions to build more complex logic.

| Operator | Name         | Description |
|:--------:|--------------|-------------|
| `&&`     | Logical AND  | `true` only if both operands are `true`. |
| `\|\|`   | Logical OR   | `true` if at least one operand is `true`. |
| `!`      | Logical NOT  | Inverts the value (`true` becomes `false` and vice versa). |
| `^`      | Logical XOR  | `true` if the operands are different. |

```java
int age = 25;
boolean isEligible = (age >= 18) && (age <= 65);
```

### Short-Circuit Evaluation

`&&` and `||` are **short-circuit** operators: the right operand is evaluated only if it is needed to determine the result.

```java
String text = null;

// Safe: if text == null the right side is never evaluated
if (text != null && text.length() > 0) {
    System.out.println("Not empty");
}
```

The operators `&` and `|` also work on `boolean` values, but they **always evaluate both operands**:

```java
int x = 0;
boolean r1 = false && (++x > 0);  // ++x is NOT executed, x stays 0
boolean r2 = false &  (++x > 0);  // ++x IS executed, x becomes 1
```

Compound assignment forms `&=`, `|=`, and `^=` are also available for `boolean` variables.

---

## 5. Use in Control Structures and Methods

`boolean` variables and expressions determine the execution path in conditional statements and loops.

### Conditional Statement (`if-else`)

```java
boolean isSenior = age > 65;
if (isSenior) {
    System.out.println("Time to retire");
} else {
    System.out.println("Standard rate");
}
```

### Ternary Operator

```java
String rate = isSenior ? "Senior rate" : "Standard rate";
```

### Loops (`while`, `for`)

```java
boolean keepRunning = true;
int counter = 0;

while (keepRunning) {
    counter++;
    if (counter >= 5) {
        keepRunning = false;
    }
}
```

### Method Return Values

A method can return `boolean` to check a condition or perform validation.

```java
public static boolean isGreaterThan(int a, int b) {
    return a > b;
}
```

---

## 6. Common Pitfall: Assignment Instead of Comparison

With `boolean`, an accidental `=` in a condition **compiles** (with `int` it would not), which makes this bug easy to miss:

```java
boolean done = false;

if (done = true) {          // assigns true, the condition is always true!
    System.out.println("Always runs");
}

if (done) { ... }           // correct and shorter
```

---

## 7. The `Boolean` Wrapper Class

`java.lang.Boolean` is the object wrapper for the primitive `boolean`. It is needed for generics and collections (`List<Boolean>`) and provides utility methods.

```java
boolean b1 = Boolean.parseBoolean("true");   // true
boolean b2 = Boolean.parseBoolean("TRUE");   // true (case-insensitive)
boolean b3 = Boolean.parseBoolean("yes");    // false (anything except "true")
boolean b4 = Boolean.parseBoolean(null);     // false

Boolean boxed = true;                        // autoboxing
// boolean value = nullBoolean;              // NullPointerException if the wrapper is null
```

---

## 8. Memory Representation

The Java Language Specification does not define an exact size for `boolean`. In practice:

- A `boolean` local variable or field is handled by the JVM as an `int`.
- In HotSpot, a `boolean[]` array uses one byte per element.

Because of this, do not rely on `boolean` for saving memory. For large sets of flags, consider `java.util.BitSet`.

---

## 9. Best Practices

- **Clear names:** use the prefixes `is`, `has`, or `can` (for example `isValid`, `hasCompleted`, `isAvailable`) so that the name reflects the meaning of the check.
- **Avoid redundant comparisons:** write `if (isTrue)` instead of `if (isTrue == true)`, and `if (!isTrue)` instead of `if (isTrue == false)`.
- **Name complex conditions:** store complex logical expressions in named `boolean` variables to make code more readable and reusable.

```java
boolean isAdult = age >= 18;
boolean hasTicket = ticketCount > 0;
boolean canEnter = isAdult && hasTicket;
```
