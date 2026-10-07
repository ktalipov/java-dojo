# Java Variables Documentation

## Overview
A variable in Java is a named container in memory that stores a data value of a specified type. It serves as a fundamental building block of a Java application, allowing programs to store, manage, and manipulate data during execution.

---

## Variable Declaration and Assignment

### Basic Syntax
A variable is declared by specifying its type and name. It can be initialized at the time of declaration or assigned later:

- **Full Syntax (Declaration then Assignment):**
  ```java
  int x;
  x = 9;
  ```
- **Short Syntax (Combined Declaration and Initialization):**
  ```java
  int x = 9;
  ```

When a variable is declared, the Java Virtual Machine (JVM) allocates memory for it under the specified name.

### Re-assignment and Modification
Once declared, a variable's data type cannot be changed. However, its value can be reassigned during runtime:

- **Overwriting Values:**
  ```java
  x = 4; // Replaces previous value with 4
  ```
- **Assigning Values from Other Variables:**
  ```java
  int y = 7;
  x = y; // x receives a copy of y's value (7)
  ```
- **Modifying via Expressions:**
  ```java
  x = y + 5; // x becomes 12
  x = x + 6; // x becomes 18
  ```
- **Compound Assignment Operator (`+=`):**
  ```java
  x += 6; // Equivalent to x = x + 6, but evaluates x only once
  ```
  > **Note:** Pay attention to syntax order. Writing `x =+ 6` is interpreted as assigning a positive number `+6` to `x`, rather than adding 6 to `x`.

### Naming Conventions
- Variable names in Java follow the **lowerCamelCase** convention (e.g., `countOfPeople`).
- The first word begins with a lowercase letter, and each subsequent word begins with a capital letter.

---

## Local Type Inference (`var`)

Starting in Java 10, local variables can be declared using the `var` keyword:

```java
var value = 9;
```

- **How it works:** The Java compiler automatically infers the data type from the initialization expression on the right-hand side.
- **Strict Typing:** The variable remains strictly typed after inference.
- **Restrictions:** `var` can only be used for **local variables** that have an explicit initializer. It cannot be used for class fields, method parameters, or variables declared without an initial value.

---

## Categories of Variables by Scope and Lifetime

In Java, variables are categorized into three main types based on where they are declared:

### 1. Instance Variables (Object Fields)
- **Declaration:** Declared inside a class, but outside any method, constructor, or code block.
- **Creation & Lifetime:** Created when an object instance is instantiated using the `new` keyword, and exist as long as the object exists.
- **Access:** Accessed through an object instance reference (e.g., `dog.value`).
- **Default Values:** Automatically initialized with default values if not explicitly assigned (e.g., `0` for numbers, `'\u0000'` for characters, `false` for booleans, `null` for object references).
- **Scope & Modifiers:** Visible to all methods and constructors within the object. Access modifiers (e.g., `public`, `private`, `protected`) are allowed.
- **Copies:** Each created object instance maintains its own separate copy of the variable.

### 2. Local Variables
- **Declaration:** Declared inside a method, constructor, or code block.
- **Creation & Lifetime:** Created when execution enters the method or block, and destroyed immediately when execution leaves that scope.
- **Access:** Accessed directly by name within the scope where declared.
- **Default Values:** **No default values.** Local variables must be explicitly assigned a value before being read. Attempting to read an uninitialized local variable causes a compilation error (`variable might not have been initialized`).
- **Scope & Modifiers:** Restricted entirely to the declaring block. Access modifiers are **not** allowed.
- **Copies:** Created per method invocation.

### 3. Class Variables (Static Variables)
- **Declaration:** Declared with the `static` modifier inside a class, but outside any method, constructor, or block.
- **Creation & Lifetime:** Created when the class is first loaded into the JVM and exist until the application stops running.
- **Access:** Accessed directly through the class name (e.g., `Dog.value`).
- **Default Values:** Automatically initialized with default values (`0`, `'\u0000'`, `false`, `null`).
- **Scope & Modifiers:** Shared across all instances of the class. Access modifiers are allowed.
- **Copies:** Exactly **one single instance** exists for the entire class, regardless of how many objects are instantiated.

---

## Comparison Summary Table

| Feature | Instance Variable | Local Variable | Class (Static) Variable |
| :--- | :--- | :--- | :--- |
| **Declaration Location** | Inside class, outside methods | Inside method, constructor, or block | Inside class, with `static` modifier |
| **Creation Time** | On object creation (`new`) | On entering method or block | On class loading into JVM |
| **Default Values** | Yes (`0`, `'\u0000'`, `false`, `null`) | No (must assign before reading) | Yes (`0`, `'\u0000'`, `false`, `null`) |
| **Access Method** | Via object reference (`dog.value`) | Direct name inside block | Via class name (`Dog.value`) |
| **Instance Count** | One copy per object instance | One copy per method call | One single copy for the entire class |
| **Access Modifiers** | Allowed | Not allowed | Allowed |

---

## Constants in Java

A constant is a variable whose value is fixed and cannot be changed after initialization.

### Defining Constants
- Constants are defined using the `final` keyword.
- They are usually declared together with `static` so that a single copy is shared across all instances of a class:
  ```java
  static final int VALUE = 54;
  ```
- Public class constants are commonly declared as `public static final`.

### Naming Conventions for Constants
- Constant names are written in **UPPERCASE** with words separated by underscores (`ALL_CAPS`).
- Example:
  ```java
  static final int MAX_VALUE = 999;
  ```

