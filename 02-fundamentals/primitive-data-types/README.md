# Java Primitive Data Types Overview

Java is a statically-typed programming language. In Java, all variables must be declared before they can be used. The language supports eight predefined **primitive data types**, which store raw values directly in memory rather than references to objects.

---

## The 8 Primitive Data Types

| Data Type | Size / Memory | Default Value | Range / Description | Wrapper Class |
| :--- | :--- | :--- | :--- | :--- |
| **byte** | 8-bit signed integer | `0` | -128 to 127 | `Byte` |
| **short** | 16-bit signed integer | `0` | -32,768 to 32,767 | `Short` |
| **int** | 32-bit signed integer | `0` | -$2^{31}$ to $2^{31}-1$ | `Integer` |
| **long** | 64-bit signed integer | `0L` | -$2^{63}$ to $2^{63}-1$ | `Long` |
| **float** | 32-bit IEEE 754 floating-point | `0.0f` | Single-precision floating point | `Float` |
| **double** | 64-bit IEEE 754 floating-point | `0.0d` | Double-precision floating point | `Double` |
| **boolean** | 1 bit logical value (JVM dependent) | `false` | `true` or `false` | `Boolean` |
| **char** | 16-bit Unicode character | `'\u0000'` | `'\u0000'` (0) to `'\uffff'` (65,535) | `Character` |

---

## Data Type Categories

### 1. Integer Types
- **byte**: Useful for saving memory in large arrays or handling raw binary data.
- **short**: A 16-bit signed integer used to save memory in large arrays.
- **int**: The standard choice for integral values unless memory savings or extended range is required.
- **long**: Used when values exceed the range of `int`. Requires an `L` suffix (e.g., `1000L`).

### 2. Floating-Point Types
- **float**: Single-precision 32-bit floating point. Requires an `f` or `F` suffix (e.g., `3.14f`).
- **double**: Double-precision 64-bit floating point. Default choice for decimal values.
> *Note:* Floating-point types should not be used for precise values such as currency.

### 3. Character Type
- **char**: Stores a single 16-bit Unicode character enclosed in single quotes (e.g., `'A'`).

### 4. Boolean Type
- **boolean**: Represents a simple flag with only two possible values: `true` and `false`.

---

## High-Level Notes

- **Default Values**: Uninitialized fields automatically receive default zero/false values, whereas uninitialized local variables cause compile-time errors.
- **Literals & Underscores**: Java supports decimal, hexadecimal (`0x`), and binary (`0b`) literals. Underscores can be placed between digits (e.g., `1_000_000`) for readability.

