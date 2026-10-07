# Integer Data Types

Java has **four primitive integer types**: `byte`, `short`, `int`, and `long`. All of them are **signed** and stored in memory using **two's complement** representation. Primitive types store their values directly, have a fixed size, and cannot hold `null`.

## Summary

| Type    | Size             | Range                                      | Default (fields) |
|---------|------------------|--------------------------------------------|------------------|
| `byte`  | 8 bits / 1 byte  | -128 to 127                                | `0`              |
| `short` | 16 bits / 2 bytes| -32,768 to 32,767                          | `0`              |
| `int`   | 32 bits / 4 bytes| -2,147,483,648 to 2,147,483,647            | `0`              |
| `long`  | 64 bits / 8 bytes| -2^63 to 2^63 - 1 (about ±9.2 quintillion) | `0L`             |

---

## 1. Type Characteristics and Usage

### `byte` (8 bits / 1 byte)

- **Range:** -128 to 127
- **Default value (class fields):** `0`
- **Notes and usage:** Intended for large arrays (`byte[]`) where saving memory is critical, for example when reading binary files, network streams, or image pixels. In arithmetic expressions the JVM automatically promotes `byte` values to `int`.

### `short` (16 bits / 2 bytes)

- **Range:** -32,768 to 32,767
- **Default value (class fields):** `0`
- **Notes and usage:** Used to save memory in large arrays, but rarely seen in everyday application code.

### `int` (32 bits / 4 bytes)

- **Range:** -2,147,483,648 to 2,147,483,647 (roughly -2.1 billion to +2.1 billion)
- **Default value (class fields):** `0`
- **Notes and usage:** The main integer type in Java. Any integer literal (for example `42`) is treated by the compiler as an `int` by default. Since Java 8, an `int` can also be handled as an unsigned 32-bit integer using special methods of the `Integer` class (for example, `Integer.compareUnsigned()`).

### `long` (64 bits / 8 bytes)

- **Range:** -2^63 to 2^63 - 1 (about -9.2 quintillion to +9.2 quintillion)
- **Default value (class fields):** `0L`
- **Notes and usage:** Used when values exceed the `int` limit, for example timestamps in milliseconds or nanoseconds (`System.currentTimeMillis()`), unique identifiers, and file sizes. A `long` literal outside the `int` range **must** have the `L` suffix.

### Initialization and Scope

- Class fields (instance or static) are automatically initialized to default values (`0` or `0L`).
- **Local variables** (declared inside methods) are **not** given default values. Reading an uninitialized local variable causes a compilation error.

```java
public class Example {
    int field;          // 0 by default

    void method() {
        int local;
        // System.out.println(local); // compilation error: variable might not have been initialized
    }
}
```

---

## 2. Two's Complement

Java uses **two's complement** to represent both positive and negative signed numbers.

### How the Sign Bit Works

The **most significant (leftmost) bit** is the sign bit:

- If it is **0**, the number is **positive**. Its binary form matches the ordinary unsigned notation (for example, `0000 0001` is `1`).
- If it is **1**, the number is **negative** (for example, `1111 1111` in a `byte` represents `-1`).

### Algorithm for Obtaining Two's Complement

To get the negative representation of a number in binary, perform two steps:

1. **Invert the bits** (bitwise NOT, `~`): every 1 becomes 0 and every 0 becomes 1.
2. **Add one** to the inverted result.

### Examples

**Converting `1` to `-1` (32-bit `int`):**

- Original number `1`: `0b0000...0001`
- Step 1 (invert, `~1`): `0b1111...1110` (this equals `-2`)
- Step 2 (add `1`): `0b1111...1110 + 1 = 0b1111...1111` (this represents `-1`)

**Converting `-2` to positive `2`:**

- Negative number `-2`: `0b1111_1111_1111_1111_1111_1111_1111_1110`
- Step 1 (invert, `~`): `0b0000_0000_0000_0000_0000_0000_0000_0001`
- Step 2 (add `1`): `0b0000...0001 + 1 = 0b0000...0010`, which is `2` in decimal

### Why Two's Complement Is Used

Two's complement lets the processor perform subtraction as ordinary addition with a negative number, without separate sign-handling logic. When the fixed bit width overflows, the extra carry out of the most significant bit is simply discarded.
