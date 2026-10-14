# Type Conversion and Casting in Java

## 1. Introduction

In Java, you often need to assign a value of one type to a variable of another type. Depending on how compatible the types are and how large they are, Java either performs an **implicit conversion** automatically or requires an **explicit cast**.

---

## 2. Automatic (implicit) type conversion

Automatic (widening) conversion happens when both of these conditions are met:

1. The two types are **compatible**.
2. The **destination type is larger** than the source type.

### Compatibility rules

- **Numeric types:** All integer and floating-point types are compatible with each other. For example, an `int` value can always be assigned to a `long` variable.
- **`char` and `boolean`:** These types are not compatible with each other. There is no automatic conversion from numeric types to `char` or `boolean`.
- **Literals:** The Java compiler automatically converts an integer literal constant when it is stored in a `byte`, `short`, `long` or `char` variable.

---

## 3. Explicit casting of incompatible types (narrowing conversion)

When you assign a value of a wider type to a variable of a narrower type (for example, `int` to `byte`), Java does not convert it automatically. In that case you use an explicit narrowing cast.

### Syntax

```java
(target_type) value
```

### Behavior of a narrowing cast

1. **Out-of-range values (modulo reduction).** If an integer value is outside the range of the target type, it is reduced modulo the size of the target type's range. For `byte`, the range size is 256. Casting `257` to `byte` gives 257 mod 256 = 1.
2. **Truncation of floating-point values.** When a floating-point value is assigned to an integer variable, the fractional part is discarded. Casting `323.142` to `int` gives `323`; the `.142` is lost. If the integer part is also outside the target type's range, it is then reduced modulo the range size. For example, `(byte) 323.142` gives `67`, because 323 mod 256 = 67.

### Demo program

```java
class Conversion {
    public static void main(String[] args) {
        byte b;
        int i = 257;
        double d = 323.142;

        System.out.println("\nConversion of int to byte.");
        b = (byte) i;
        System.out.println("i and b: " + i + " " + b); // Output: 257 and 1

        System.out.println("\nConversion of double to int.");
        i = (int) d;
        System.out.println("d and i: " + d + " " + i); // Output: 323.142 and 323

        System.out.println("\nConversion of double to byte.");
        b = (byte) d;
        System.out.println("d and b: " + d + " " + b); // Output: 323.142 and 67
    }
}
```

---

## 4. Automatic type promotion in expressions

When an expression is evaluated, the precision of intermediate values can exceed the range of the operands. To prevent overflow, Java automatically promotes operand types.

### Basic promotion to `int`

All `byte`, `short` and `char` values are automatically promoted to `int` when an expression is evaluated.

This code does not compile:

```java
byte b = 50;
b = b * 2; // Error! The result of b * 2 is an int
```

Because `b` is promoted to `int`, the result of `b * 2` is an `int`, which cannot be assigned to a `byte` without an explicit cast. The correct version is:

```java
b = (byte)(b * 2); // Correct
```

### Promotion rules for binary operations

1. All `byte`, `short` and `char` values are promoted to `int`.
2. If one operand is `long`, the whole expression is promoted to `long`.
3. If one operand is `float`, the whole expression is promoted to `float`.
4. If any operand is `double`, the result is `double`.

### Example of combined promotion

```java
class Promote {
    public static void main(String[] args) {
        byte b = 42;
        char c = 'a';
        short s = 1024;
        int i = 50000;
        float f = 5.67f;
        double d = .1234;

        double result = (f * b) + (i / c) - (d * s);
        System.out.println("result = " + result);
    }
}
```

#### Step-by-step analysis of `(f * b) + (i / c) - (d * s)`

- **`f * b`**: `b` is promoted to `float`, so the subexpression is a `float`.
- **`i / c`**: `c` is promoted to `int`, so the subexpression is an `int`.
- **`d * s`**: `s` is promoted to `double`, so the subexpression is a `double`.
- **`float + int`**: the intermediate result is promoted to `float`.
- **`float - double`**: the final result is promoted to `double`.
