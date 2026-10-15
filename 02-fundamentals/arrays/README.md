# Arrays in Java

## 1. Introduction and Key Properties

**An array in Java** is a fixed-size container object that stores elements of a single type. The length of an array is set when it is created and cannot be changed afterwards. The array is a fundamental data structure in Java, and many standard library classes are built on top of it (for example, `ArrayList`, `StringBuilder`, and the internal representation of strings).

### Main Characteristics
- **Fixed size:** The length is determined at allocation time and never changes.
- **Type homogeneity:** All elements belong to the same base type (primitive or reference type).
- **Zero-based indexing:** Valid indices range from `0` to `length - 1`.
- **Object nature:** An array is created on the heap and is referenced by a variable. All arrays inherit from `java.lang.Object` and implement the `Cloneable` and `java.io.Serializable` interfaces.
- **Random access (O(1)):** Reading and writing an element by index takes constant time.
- **Default values:** Elements are automatically initialized to the default value of their type (`0`, `0.0`, `false`, `'\u0000'`, or `null`).

---

## 2. Declaration, Creation, and Initialization

Creating an array involves three steps:
1. Declaring the array variable.
2. Allocating memory for the array object with the `new` operator.
3. Initializing the elements with values.

### Ways to Declare and Create

```java
// 1. Recommended style (brackets after the type)
int[] numbers; 

// 2. Alternative C-style syntax (brackets after the name)
int legacy[]; 

// 3. Memory allocation with the new operator
numbers = new int[5]; // All 5 elements are 0

// 4. Array initializer (creation and population)
int[] primes = {2, 3, 5, 7, 11}; 

// 5. Full form with an explicit type (anonymous array)
int[] data = new int[]{10, 20, 30};

// 6. Type inference (Java 10+)
var arr = new int[10];
```

> **Important:** The literal `{1, 2, 3}` without `new int[]` is allowed only in the declaration of a variable. To assign to an already declared variable or to pass an argument to a method, use the form `new int[]{...}`.

---

## 3. Memory Layout of an Array

An array-typed variable stores only a **reference**. The array object itself is allocated on the heap.

### Structure of an Array Object on the Heap
- **Components (elements):** Unnamed variables accessible by index.
- **The `length` field:** A `public final int` field containing the length of the array.
- **Methods of the `Object` class:** `toString()`, `equals()`, `hashCode()`, `getClass()`, and the overridden `clone()` method.

### Primitives and References in Arrays
| Array type | What the cells store | Default value |
| :--- | :--- | :--- |
| `int[]`, `byte[]`, `short[]`, `long[]` | The numeric values themselves | `0` / `0L` |
| `double[]`, `float[]` | Floating-point values | `0.0` / `0.0f` |
| `boolean[]` | Boolean values | `false` |
| `char[]` | Unicode characters | `'\u0000'` |
| `String[]`, `Object[]` | References to objects on the heap | `null` |

---

## 4. Element Access and Exceptions

Elements are accessed using the `array[index]` syntax. The array length is available through the **field** `length` (without parentheses).

### Exceptions When Working with Arrays
- `ArrayIndexOutOfBoundsException`: Thrown when accessing an index less than `0` or not less than `array.length`.
- `NegativeArraySizeException`: Thrown when attempting to create an array with a negative size (for example, `new int[-1]`).
- `NullPointerException`: Thrown when attempting to access the `length` field or an element of a `null` variable.
- `ArrayStoreException`: Thrown when attempting to store an incompatible object type in an array of a supertype's references.
- `OutOfMemoryError`: Thrown when the heap does not have enough memory to allocate an array of the requested size.

---

## 5. Ways to Iterate Over Elements

### 1. Classic `for` loop by index
Used when you need the index, want to modify elements in place, or iterate with a special step or in reverse order:
```java
int[] arr = {10, 20, 30};
for (int i = 0; i < arr.length; i++) {
    arr[i] *= 2;
}
```

### 2. Enhanced `for-each` loop
Used for sequential reading without modifying the original array values:
```java
for (int val : arr) {
    System.out.println(val);
}
```

### 3. Java Streams API
For functional processing, filtering, and aggregation:
```java
int sum = Arrays.stream(arr).sum();
double avg = Arrays.stream(arr).average().orElse(0.0);
```

---

## 6. Multidimensional and Jagged Arrays

Java has no "true" contiguous multidimensional arrays: a multidimensional array is an **array of arrays**.

### Two-Dimensional Rectangular Arrays
```java
int[][] matrix = new int[3][4]; // 3 rows of 4 columns each
```

### Jagged Arrays
Rows of different lengths are achieved by allocating memory for the second dimension separately:
```java
int[][] jagged = new int[3][];
jagged[0] = new int[1];
jagged[1] = new int[3];
jagged[2] = new int[2];
```

---

## 7. Passing to Methods and Returning Values

### Passing the Reference by Value
Java passes arguments **by value**. For an array, the value is the reference itself. Changing elements inside a method is reflected in the original array, while reassigning the parameter's reference is local to the method:
```java
static void modifyElements(int[] arr) {
    arr[0] = 99; // The change is visible outside
}

static void reassignReference(int[] arr) {
    arr = new int[]{1, 2, 3}; // Does not affect the outer variable
}
```

### Varargs (Variable Number of Arguments)
The signature `Type... name` is compiled into an array `Type[]`:
```java
static int sum(int... numbers) {
    int total = 0;
    for (int n : numbers) total += n;
    return total;
}
```

---

## 8. Copying Arrays

Assignment `b = a` copies only the reference. To create an independent copy, use:
- `a.clone()` — makes a shallow copy (sufficient for primitives; for objects and matrices, the references are copied).
- `Arrays.copyOf(a, newLength)` — copies with the ability to change the length.
- `Arrays.copyOfRange(a, from, to)` — copies a specific range.
- `System.arraycopy(src, srcPos, dest, destPos, length)` — a high-performance native method for copying into an existing array.

---

## 9. The `java.util.Arrays` Helper Class

The utility class `java.util.Arrays` provides methods for working with arrays:
- **Sorting:** `Arrays.sort(arr)`, `Arrays.parallelSort(arr)`
- **Binary search:** `Arrays.binarySearch(sortedArr, key)` (requires the array to be sorted beforehand)
- **Filling:** `Arrays.fill(arr, val)`, `Arrays.setAll(arr, i -> i * 2)`
- **Comparison:** `Arrays.equals(a, b)`, `Arrays.deepEquals(m1, m2)`
- **String representation:** `Arrays.toString(a)`, `Arrays.deepToString(matrix)`
- **Conversion to a list:** `Arrays.asList(array)` (returns a fixed-size list view)

---

## 10. Covariance and Limitations with Generics

- **Covariance:** Arrays are covariant (`Integer[]` is a subtype of `Number[]`). This allows assigning an `Integer[]` to a `Number[]` variable, but attempting to store a `Double` causes an `ArrayStoreException` at runtime.
- **Generics limitation:** Creating an array of a generic type directly is prohibited (for example, `new List<String>[10]` will not compile), because generics are erased at runtime while arrays require type checking at runtime.

---

## 11. Array (`T[]`) vs `ArrayList<T>`

| Criterion | Array (`T[]`) | `ArrayList<T>` |
| :--- | :--- | :--- |
| **Size** | Fixed at creation | Dynamic (grows automatically) |
| **Primitives** | Supported directly (`int[]`) | Wrapper types only (`ArrayList<Integer>`) |
| **Performance** | Maximum, compact storage | Small overhead |
| **Type checking** | At runtime (covariance) | At compile time (generics) |
| **Convenience** | Requires manual work when changing length | Ready-made methods (`add`, `remove`, `contains`) |

---

## 12. Best Practices

1. Put the square brackets after the type (`int[] arr`), not after the variable name.
2. Return an empty array (`new int[0]`) instead of `null` from methods.
3. Use `for-each` when the element index is not needed.
4. Make defensive copies (`clone()`) if a class stores or returns an internal array.
5. Always use `Arrays.equals` or `Arrays.deepEquals` to compare array contents.
