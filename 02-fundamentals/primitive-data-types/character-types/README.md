# The `char` Type in Java: ASCII, Unicode and Text Encoding

## 1. Introduction and Evolution of Encoding Systems

Text representation in computers evolved from simple 7-bit tables mapping numbers to characters to international standards that define a universal code space. For a Java developer this history matters: how characters are physically represented affects the compiler, string handling correctness, performance, and portability.

This document covers three key building blocks of text processing:

- **ASCII**: the basic American standard for information interchange.
- **Unicode**: the universal code space and its transformation formats (UTF-8, UTF-16, UTF-32).
- **The Java `char` type**: its specification in the *Java Language Specification (JLS, Chapter 3)*, its internals, arithmetic, surrogate pairs, and the `java.lang.Character` wrapper class.

---

## 2. ASCII: The 7-Bit Standard

### 2.1. History and Purpose

**ASCII** (*American Standard Code for Information Interchange*) was published in 1963 by the American Standards Association (ASA, later ANSI) as ASA X3.4-1963. Its final code table took shape in the 1967 revision (USAS X3.4-1967). It was created to unify data exchange between teletypes and computers from different vendors, which used incompatible 6-bit encodings (Fieldata, BCDIC) or the Hollerith punched-card code.

ASCII is a **7-bit code** that defines 128 code positions (`0x00` to `0x7F` in hex, or `0` to `127` in decimal). Each character is encoded as a 7-bit binary number.

### 2.2. Structure of the ASCII Table

The 128 positions are split into four groups of 32:

| Range (Hex)     | Range (Dec) | Category                | Contents |
|-----------------|-------------|-------------------------|----------|
| `0x00` – `0x1F` | 0 – 31      | Control codes           | Non-printable commands for teletypes, printers, and network protocols (plus `DEL` at `0x7F`). |
| `0x20` – `0x3F` | 32 – 63     | Digits and punctuation  | Starts with space (`0x20`); contains punctuation, the digits `0`–`9` (`0x30`–`0x39`), and operators. |
| `0x40` – `0x5F` | 64 – 95     | Uppercase Latin letters | Starts with `@` (`0x40`); letters `A`–`Z` (`0x41`–`0x5A`) and the symbols `[`, `\`, `]`, `^`, `_`. |
| `0x60` – `0x7F` | 96 – 127    | Lowercase Latin letters | Starts with `` ` `` (`0x60`); letters `a`–`z` (`0x61`–`0x7A`), the symbols `{`, `\|`, `}`, `~`, and `DEL` (`0x7F`). |

**Useful properties of the structure:**

1. **Case switching by flipping one bit.** Lowercase letters are offset by exactly 32 from uppercase (`'A'` = `0x41` = `01000001₂`, `'a'` = `0x61` = `01100001₂`). Flipping the bit with value `0x20` (the 6th bit) switches the case.
2. **Easy digit-to-BCD conversion.** The digits `0`–`9` occupy `0x30`–`0x39` (`0110000₂`–`0111001₂`). Dropping the three high bits `011₂` immediately gives the 4-bit binary-coded decimal (BCD) value.

### 2.3. ASCII Control Characters

The first 32 positions are control commands. For historical reasons many were meant for mechanical devices such as teletypes and paper tape readers:

- **NUL (`0x00`)**: the null character. On paper tape it meant "no holes punched". In C/C++ it marks the end of a string (null-terminated string).
- **LF (`0x0A`, `\n`)**: line feed. Moves the print head down one line. The standard line separator on UNIX systems.
- **CR (`0x0D`, `\r`)**: carriage return. Moves the carriage to the start of the line. Ended a line in classic Mac OS; MS-DOS/Windows uses the pair `CR LF` (`\r\n`).
- **HT (`0x09`, `\t`)**: horizontal tab.
- **BS (`0x08`, `\b`)**: backspace. Allowed overprinting, for example a letter plus an underscore to get underlined or bold text.
- **ESC (`0x1B`)**: escape. Starts a control sequence (ANSI codes).
- **DEL (`0x7F`)**: delete. On paper tape all 7 bits were punched (`1111111₂`), which "voided" a previously punched character.

### 2.4. 8-Bit Extensions and the Fragmentation Problem

When computers moved to 8-bit bytes (octets), the eighth bit of ASCII characters was initially zero. Localization needs then led to 8-bit code pages (*Extended ASCII*) with 256 positions (`0x00`–`0xFF`).

The lower half (`0x00`–`0x7F`) stayed compatible with ASCII, while the upper half (`0x80`–`0xFF`) was used for national alphabets (Cyrillic, Greek, Arabic). This produced dozens of incompatible standards. For Cyrillic alone: KOI8-R, Windows-1251, CP866 (the MS-DOS "alternative" code page), ISO/IEC 8859-5, MacCyrillic.

Without a common standard for the upper half, a document opened with the wrong code page was displayed as garbage text ("mojibake").

---

## 3. Unicode: The Universal Code Space

### 3.1. Concept and Scale

**Unicode** was developed by the Unicode Consortium together with ISO (standard ISO/IEC 10646, the *Universal Coded Character Set*, UCS) to create a single encoding covering all the world's writing systems, historical scripts, mathematical symbols, and emoji.

Unicode defines a code space from **`U+0000` to `U+10FFFF`**, which gives **1,114,112 code points**. For backward compatibility, the first 128 Unicode characters (`U+0000`–`U+007F`) are identical to ASCII.

### 3.2. Unicode Planes

The code space is divided into **17 planes**, each with 2^16 = 65,536 code points:

1. **Plane 0 (`U+0000` – `U+FFFF`): Basic Multilingual Plane (BMP).** Characters of practically all modern languages (Latin, Cyrillic, Greek, Arabic, Hebrew, Armenian, the main CJK ideographs).
2. **Plane 1 (`U+10000` – `U+1FFFF`): Supplementary Multilingual Plane (SMP).** Historical scripts (Egyptian hieroglyphs, Sumerian cuneiform), emoji, musical notation.
3. **Plane 2 (`U+20000` – `U+2FFFF`): Supplementary Ideographic Plane (SIP).** Rare and historical Chinese, Japanese, and Korean ideographs.
4. **Plane 3 (`U+30000` – `U+3FFFF`): Tertiary Ideographic Plane (TIP).** Additional CJK ideographs.
5. **Planes 4 – 13 (`U+40000` – `U+DFFFF`):** currently unassigned.
6. **Plane 14 (`U+E0000` – `U+EFFFF`): Supplementary Special-purpose Plane.** Tag characters and variation selectors.
7. **Planes 15 and 16 (`U+F0000` – `U+10FFFF`):** Private Use Areas (PUA).

### 3.3. Code Point, Code Unit, and Grapheme

Unicode strictly separates three concepts:

- **Code point**: the unique number assigned to a character in Unicode, written `U+xxxx`. Range: `U+0000` to `U+10FFFF`. Example: "😀" has the code point `U+1F600`.
- **Code unit**: a fixed-size bit sequence used by a specific transformation format (UTF-8, UTF-16, UTF-32) to encode a code point. In UTF-16 a code unit is a 16-bit value.
- **Grapheme (grapheme cluster)**: the smallest unit of writing perceived by a person as one character. One grapheme can consist of several code points.

> **Example:** the flag of Russia (🇷🇺) consists of two regional indicator code points (`U+1F1F7` and `U+1F1FA`). In UTF-16 it takes **four** 16-bit code units (4 Java `char` values), but the user sees **one** grapheme.

### 3.4. Transformation Formats (UTF-8, UTF-16, UTF-32)

| Format     | Code unit size  | Bytes per character | Advantages and usage |
|------------|-----------------|---------------------|----------------------|
| **UTF-8**  | 8 bits (1 byte) | Variable (1–4)      | Backward compatible with ASCII (ASCII characters take 1 byte). Compact for English text. The dominant encoding on the Internet. |
| **UTF-16** | 16 bits (2 bytes) | Variable (2 or 4) | BMP characters take one 16-bit unit (2 bytes). Characters outside the BMP take a pair of units (a surrogate pair, 4 bytes). **The logical string representation in Java and Windows.** |
| **UTF-32** | 32 bits (4 bytes) | Fixed (4)         | Every code point takes exactly 32 bits, so code points can be indexed in O(1), but it uses 2–4 times more memory. |

> **Note:** since Java 9, the JVM may store strings internally in a compact form (one byte per character for Latin-1 text). This is an implementation detail: from the programmer's point of view, a `String` is still a sequence of UTF-16 code units.

---

## 4. The `char` Type in the Java Specification (JLS Chapter 3)

### 4.1. `char` Among the Primitive Types

Java has 8 primitive types. `char` is an **integral type**.

> **`char` specification:** a **16-bit unsigned integer**.
> - **Range:** `0` to `65,535` (hex: `'\u0000'` to `'\uffff'`).
> - **Size:** 16 bits (2 bytes).
> - **Default value for fields:** `'\u0000'` (numeric `0`).

When Java was created in the mid-1990s, Unicode was designed as a fixed 16-bit code, so `char` was meant to hold exactly one Unicode character. When Unicode grew beyond `U+FFFF`, a Java `char` became strictly **one UTF-16 code unit**.

### 4.2. Lexical Translations by the Compiler (JLS §3.2)

According to *JLS Chapter 3 (Lexical Structure)*, Java source code is a stream of Unicode characters. The compiler (`javac`) processes this raw stream in **three consecutive lexical translation steps**:

```
[Stream of Unicode characters]
       │
       ▼
 1. Unicode escape translation (\uxxxx)  ──► \uxxxx converted to UTF-16 code units
       │
       ▼
 2. Line terminator recognition          ──► CR, LF, CR LF
       │
       ▼
 3. Tokenization                         ──► tokens; white space and comments removed
```

1. **Step 1: Unicode escapes (`\uxxxx`).** Every sequence `\uxxxx` (where `x` is a hex digit) is translated into the corresponding 16-bit UTF-16 code unit. This makes it possible to write any Java program using only ASCII characters.
   - *Backslash rule:* the compiler counts consecutive `\` characters. If their number is even, the following `\` can start a Unicode escape; if it is odd, the `\` is an escaped raw character.
2. **Step 2: Line terminators.** The stream is split into lines at `LF`, `CR`, or the pair `CR LF`.
3. **Step 3: Tokenization.** The stream becomes a sequence of tokens: identifiers, keywords, literals, separators, and operators. White space and comments are discarded. The **longest match rule** applies.

### 4.3. The Early Unicode Escape Trap

Because `\uxxxx` is translated in the **first** step, before the compiler analyzes strings, comments, and literals, some Unicode escapes cause compilation errors.

**Trap 1: line feed `\u000a`**

```java
// COMPILATION ERROR!
char nl = '\u000a';
```

In step 1 the compiler replaces `\u000a` with a real line feed. In step 2 that character splits the literal across two lines:

```java
char nl = '
';
```

Step 3 then fails with `error: illegal line end in character literal`.

**Trap 2: double quote `\u0022`**

In the same way, `"\u0022"` becomes a plain quote `"` in step 1 and closes the string literal too early.

To put special characters into literals, use the **escape sequences of character literals** (processed in step 3): `'\n'`, `'\r'`, `'\''`, `'"'`.

---

## 5. Working with `char` and Arithmetic

### 5.1. Declaration and Literals

A `char` variable can be initialized with a character literal in single quotes, a hex Unicode escape, or an integer from `0` to `65535`:

```java
char ch1 = 'A';        // character literal (code 65)
char ch2 = 1071;       // decimal code (the letter 'Я')
char ch3 = '\u042F';   // Unicode escape (the letter 'Я')
char ch4 = '\t';       // escape sequence for tab
```

**Standard escape sequences for `char`:**

| Escape | Name                 | Code            |
|--------|----------------------|-----------------|
| `'\n'` | Line Feed (LF)       | `0x0A` (10)     |
| `'\r'` | Carriage Return (CR) | `0x0D` (13)     |
| `'\t'` | Horizontal Tab       | `0x09` (9)      |
| `'\b'` | Backspace            | `0x08` (8)      |
| `'\f'` | Form Feed            | `0x0C` (12)     |
| `'\''` | Single quote         | `0x27` (39)     |
| `'\\'` | Backslash            | `0x5C` (92)     |
| `'\"'` | Double quote         | `0x22` (34)     |

### 5.2. Arithmetic and Binary Numeric Promotion

In the specification, `char` is an integral type, so all arithmetic and bitwise operations apply to it (`+`, `-`, `*`, `/`, `%`, `++`, `--`, `&`, `|`, `^`, `<<`, `>>`).

However, in arithmetic expressions **binary numeric promotion** applies: `char` operands are automatically widened to 32-bit **`int`**.

**Adding characters:**

```java
char a = 'a'; // code 97
char b = 'b'; // code 98

System.out.println(a + b);      // 195 (97 + 98, result type: int)
System.out.println("" + a + b); // "ab" (string concatenation)
```

**Type error on assignment (`c = c + 1`):**

```java
char c = 'a';

// COMPILATION ERROR: incompatible types: possible lossy conversion from int to char
// c = c + 1;

// FIXES:
c = (char) (c + 1); // explicit cast
c++;                // increment works without errors
c += 1;             // compound assignment includes an implicit cast
```

The increment/decrement (`++`, `--`) and compound assignment (`+=`, `-=`) operators automatically narrow the result back to `char`, so `c++` compiles.

### 5.3. Converting a Digit Character to a Number

Casting a digit character such as `'7'` to `int` gives its **character code**, not the digit:

```java
char digit = '7';

int code   = (int) digit;                       // 55 (code of '7')
int value1 = digit - '0';                       // 7  (55 - 48)
int value2 = Character.getNumericValue(digit);  // 7
```

---

## 6. Surrogate Pairs and Characters Outside the BMP

### 6.1. Anatomy of a Surrogate Pair

Unicode code points go up to `U+10FFFF`, so characters from planes 1–16 (emoji, rare ideographs, historical scripts) do not fit into one 16-bit `char` (`0x0000`–`0xFFFF`).

In UTF-16, and therefore in Java, such *supplementary characters* are represented by a **surrogate pair**: two consecutive 16-bit `char` values.

1. **High surrogate:** a `char` in the range **`\uD800` .. `\uDBFF`**.
2. **Low surrogate:** a `char` in the range **`\uDC00` .. `\uDFFF`**.

**Example: the emoji "😀" (`U+1F600`)** is encoded as the surrogate pair `\uD83D\uDE00`:

- High surrogate = `\uD83D`
- Low surrogate = `\uDE00`

### 6.2. `String.length()` and Counting Characters Correctly

`String.length()` returns the number of `char` code units, not the number of actual characters (code points).

```java
String emoji = "Hi 😀";

System.out.println(emoji.length());                          // 5 ('H', 'i', ' ' and 2 chars for 😀)
System.out.println(emoji.codePointCount(0, emoji.length())); // 4 (real number of characters)
```

### 6.3. Pitfalls When Iterating and Reversing Strings

If you reverse a string char by char using `charAt(i)` or `toCharArray()`, the surrogate pair is split and swapped (the low surrogate ends up before the high one). This produces an invalid UTF-16 sequence and shows up as replacement characters.

```java
String s = "A😀B"; // 'A', High, Low, 'B'

// INCORRECT: iterating over char splits the emoji
for (int i = 0; i < s.length(); i++) {
    char c = s.charAt(i);
    // at i = 1 we get \uD83D (half of the emoji)
}

// CORRECT: iterate over code points
s.codePoints().forEach(cp -> {
    System.out.printf("U+%04X (chars: %d)%n", cp, Character.charCount(cp));
});

// CORRECT: StringBuilder.reverse() keeps surrogate pairs intact
String reversed = new StringBuilder(s).reverse().toString(); // "B😀A"
```

> **Tip:** if your source file contains literal emoji, compile it as UTF-8 (`javac -encoding UTF-8`; UTF-8 is the default since Java 18).

---

## 7. The `java.lang.Character` Wrapper Class

### 7.1. Purpose

`java.lang.Character` is the standard object wrapper for the primitive `char`. It is needed for generics and collections (`List<Character>`, `Map<Character, Integer>`), and it provides many static utility methods for analyzing and transforming characters according to Unicode.

### 7.2. Key Analysis Methods

```java
char ch = 'Я';

boolean isLetter = Character.isLetter(ch);        // true
boolean isDigit  = Character.isDigit('3');        // true
boolean isSpace  = Character.isWhitespace('\t');  // true
boolean isUpper  = Character.isUpperCase(ch);     // true
char lower       = Character.toLowerCase(ch);     // 'я'
```

> **Important:** `Character.isLetter()` and `Character.isDigit()` follow the whole Unicode standard, not just ASCII. For example, `Character.isDigit('٣')` (the Arabic-Indic digit three) returns `true`. To check strictly for ASCII digits, use the explicit range `ch >= '0' && ch <= '9'`.
>
> The `char` versions of these methods cannot handle supplementary characters. For those, use the overloads that take an `int` code point, for example `Character.isLetter(int codePoint)`.

### 7.3. Code Point and Surrogate Utilities

| Method                                | Returns   | Description |
|---------------------------------------|-----------|-------------|
| `Character.isHighSurrogate(char ch)`  | `boolean` | Checks whether the `char` is a high surrogate (`\uD83D` and so on). |
| `Character.isLowSurrogate(char ch)`   | `boolean` | Checks whether the `char` is a low surrogate. |
| `Character.isSurrogate(char ch)`      | `boolean` | Checks whether the `char` is in the surrogate range. |
| `Character.charCount(int codePoint)`  | `int`     | Returns `1` for BMP characters and `2` for supplementary characters. |
| `Character.toChars(int codePoint)`    | `char[]`  | Converts a 32-bit code point into a `char` array (1 or 2 elements). |
| `Character.getName(int codePoint)`    | `String`  | Returns the official Unicode name (for example `"CYRILLIC CAPITAL LETTER YA"`). |

---

## 8. Summary and Recommendations

1. **Choosing a type in Java**
   - Use the primitive **`char`** for single ASCII values, flags, or separators.
   - Use **`String`** for user text, names, and messages.
   - When text may contain emoji or rare characters, work with **`int`** code points and the **`codePoints()`** stream.
2. **Arithmetic with `char`**
   - Remember the promotion to `int` (`a + b` is an `int`).
   - To modify a `char` variable, use `c++` or an explicit cast `(char) (c + 1)`.
3. **Escape safety**
   - Avoid the Unicode escapes `\u000a` and `\u000d` in source code. Use `'\n'` and `'\r'` instead.
