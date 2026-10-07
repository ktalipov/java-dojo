# Java Floating-Point Types

## 1. Overview of Floating-Point Types

Java has two primitive types for fractional (real) numbers: **`float`** and **`double`**. Both implement the international **IEEE 754** standard, which guarantees consistent behavior of arithmetic operations across platforms.

| Characteristic                | `float`                                         | `double`                                        |
|-------------------------------|-------------------------------------------------|-------------------------------------------------|
| **Size in memory**            | 32 bits (4 bytes)                               | 64 bits (8 bytes)                               |
| **Precision**                 | ~7 significant decimal digits                   | ~15 significant decimal digits                  |
| **Range**                     | about ±3.4e+38                                  | about ±1.7e+308                                 |
| **Smallest positive value**   | `Float.MIN_VALUE` ≈ 1.4e-45                     | `Double.MIN_VALUE` ≈ 4.9e-324                   |
| **Default value (fields)**    | `0.0f`                                          | `0.0d` (or `0.0`)                               |
| **Typical use**               | Saving memory in large arrays, embedded systems | General-purpose and scientific calculations     |

---

## 2. Internal Representation (IEEE 754)

According to IEEE 754, a real number is stored in exponential form:

```
(-1)^s × M × 2^E
```

The number is split into three parts:

1. **Sign (`s`)**: 1 bit (`0` = positive, `1` = negative).
2. **Exponent (`E`)**: encodes the power of two, stored with an offset (bias).
3. **Mantissa (`M`, significand)**: a fixed number of bits holding the significant digits of the number.

| Part     | `float` | `double` |
|----------|---------|----------|
| Sign     | 1 bit   | 1 bit    |
| Exponent | 8 bits  | 11 bits  |
| Mantissa | 23 bits | 52 bits  |

### Normalized and Subnormal Numbers

- **Normalized form**: the leading bit of the mantissa is always `1`. Because it never changes, it is not stored (the *implicit leading one*). This saves one bit and makes the representation of each number unique.
- **Subnormal (denormalized) form**: if all exponent bits are `0`, the leading bit of the mantissa is taken to be `0`. This makes it possible to encode extremely small values closer to zero.

To get the bit representation of a `double` in Java, use `Double.doubleToLongBits()` and `Double.longBitsToDouble()` (for `float`: `Float.floatToIntBits()` and `Float.intBitsToFloat()`).

```java
double d = 0.1;
long bits = Double.doubleToLongBits(d);
System.out.println(Long.toBinaryString(bits));
```

---

## 3. Calculation Errors and Precision

### Why Fractional Operations Are Inexact

The binary representation cannot express many familiar decimal fractions exactly (for example `0.1` or `0.2`). In binary they become infinite repeating fractions, just as `1/3` cannot be written exactly in decimal.

Classic examples of rounding errors in Java:

```java
System.out.println(2.0 - 1.1);   // 0.8999999999999999

double sum = 0;
for (int i = 0; i < 10; i++) {
    sum += 0.1;
}
System.out.println(sum);         // 0.9999999999999999, not 1.0
```

### ULP (Unit in the Last Place)

There are infinitely many real numbers, but only a finite number of them can be represented by `float` or `double`. The distance between two adjacent representable numbers is called **ULP** (Unit in the Last Place, also called Unit of Least Precision).

- For example, the interval from `1.0` to `2.0` contains exactly **8,388,609** `float` values (both endpoints included).
- Use `Math.nextUp()` to get the next representable number, and `Math.ulp()` to get the gap size.

```java
System.out.println(Math.nextUp(1.0));  // 1.0000000000000002
System.out.println(Math.ulp(1.0));     // 2.220446049250313E-16
```

### Financial Calculations

`float` and `double` must **never** be used for exact calculations such as money or currency. Use **`java.math.BigDecimal`** instead.

```java
import java.math.BigDecimal;

BigDecimal a = new BigDecimal("0.1");   // create from a String, not from a double
BigDecimal b = new BigDecimal("0.2");
System.out.println(a.add(b));           // 0.3
```

### Comparing Floating-Point Numbers

Do not compare results of calculations with `==`. Compare the difference with a small tolerance instead:

```java
double x = 0.1 + 0.2;
System.out.println(x == 0.3);                      // false
System.out.println(Math.abs(x - 0.3) < 1e-9);      // true
```

---

## 4. Special Values and Edge Cases

### Division by Zero

Unlike integer arithmetic, where division by zero throws `ArithmeticException`, dividing a floating-point number by `0.0` produces a special value:

| Expression    | Result                       |
|---------------|------------------------------|
| `1.0 / 0.0`   | `Double.POSITIVE_INFINITY`   |
| `-1.0 / 0.0`  | `Double.NEGATIVE_INFINITY`   |
| `0.0 / 0.0`   | `Double.NaN` (Not a Number)  |

`NaN` is not equal to anything, including itself. Use `Double.isNaN(x)` to check for it:

```java
double nan = 0.0 / 0.0;
System.out.println(nan == nan);          // false
System.out.println(Double.isNaN(nan));   // true
```

### Behavior of `MIN_VALUE` and `MAX_VALUE`

- **`Double.MIN_VALUE`** is the **smallest positive nonzero value** (the one closest to zero), not the most negative number.
- The expression `0.0 > Double.MIN_VALUE` returns `false`.
- The most negative `double` value is `-Double.MAX_VALUE`.

### Parsing Limits and Overflow

- `Double.MAX_VALUE` is `1.7976931348623157E308`.
- When parsing strings, values slightly above `MAX_VALUE` may be rounded down to it:
  - `Double.parseDouble("1.7976931348623158E308")` returns `Double.MAX_VALUE`.
  - `Double.parseDouble("1.7976931348623159E308")` returns `Infinity`.
- Operations whose result is smaller than the minimum representable value cause **underflow**, and the result becomes `0.0`.

---

## 5. Literal Syntax and Initialization

### Notation

- **`float`**: the literal must end with the suffix `f` or `F` (for example `123.4f`).
- **`double`**: a literal is a `double` by default (it may have the suffix `d` or `D`).
- **Scientific notation**: use `e` or `E` (for example `1.234e2` is `123.4`).
- **Hexadecimal literals**: since JDK 1.5, a floating-point number can be written in hex, with the exponent given by `p` (for example `0x1.0p-3` is `0.125`).

```java
float  f1 = 123.4f;
double d1 = 123.4;
double d2 = 1.234e2;      // 123.4
double d3 = 0x1.0p-3;     // 0.125

// float f2 = 123.4;      // compilation error: double cannot be converted to float
```

### Digit Separators

Since Java 7, underscores `_` can be used inside numeric literals to group digits:

```java
float pi = 3.14_15F;
```

**Restrictions:** an underscore cannot be placed at the beginning or end of a number, next to the decimal point, or before the suffixes `F`/`D`/`L`.

### Variable Initialization

- Class fields without explicit initialization automatically get `0.0f` or `0.0d`.
- Local variables are not initialized automatically; reading an uninitialized local variable causes a compilation error.

---

## 6. Portability and the `strictfp` Modifier

To be fully machine-independent, floating-point calculations should produce the same results on any virtual machine.

- **The 80-bit register problem**: some processor architectures (notably x86 with a math coprocessor, the FPU) use 80-bit intermediate registers. The extra precision in intermediate steps, followed by rounding to 64 bits, can produce results that differ from pure 64-bit processors.
- **The `strictfp` keyword**: introduced in JDK 1.2 for classes, interfaces, and methods. It guarantees that intermediate calculations strictly follow the 32/64-bit precision rules on all platforms.

> **Note:** since **Java 17** (JEP 306), all floating-point calculations are strict by default. The `strictfp` modifier is no longer needed there and the compiler warns that it is redundant. You will mostly see it in older code.

---

## 7. Recommendations: Choosing a Type and Performance

1. **Performance**: on modern 64-bit hardware, the speed of `float` and `double` operations is practically the same.
2. **Memory**: choose `float` if saving memory matters, for example when storing huge arrays of real numbers.
3. **General-purpose calculations**: `double` is the preferred default type.
4. **Exact decimal values (money)**: use `BigDecimal`, not `float` or `double`.
