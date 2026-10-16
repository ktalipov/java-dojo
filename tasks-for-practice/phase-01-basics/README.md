# Java Basics: 10 Problems

## 1. Split the Bill

Friends want to split a bill equally, including the tip. You are given the bill amount, the tip percentage, and the number of friends. Calculate the tip amount, the total amount, and the amount per person.

**Input:**
```
bill = 2500.0
tipPercent = 10.0
friends = 4
```

**Output:**
```
tip = 250.0
total = 2750.0
perPerson = 687.5
```

---

## 2. Time Converter

You are given a number of seconds. Calculate how many full hours, minutes, and remaining seconds it contains. Loops and conditionals are not allowed, only `/` and `%`.

**Input:**
```
totalSeconds = 3672
```

**Output:**
```
hours = 1
minutes = 1
seconds = 12
```

---

## 3. Binary to Decimal

You are given an eight-bit binary number written as an `int`. Extract each digit into a separate variable and calculate the decimal value manually using powers of two. Loops, conditionals, and built-in conversion methods are not allowed.

**Input:**
```
binary = 10110110
```

**Output:**
```
182
```

---

## 4. Temperature Lab

Convert a temperature from Celsius to Fahrenheit and Kelvin.

Formulas: `F = C * 9 / 5 + 32`, `K = C + 273.15`

**Input:**
```
celsius = 25.0
```

**Output:**
```
fahrenheit = 77.0
kelvin = 298.15
```

---

## 5. Salary Calculator

You are given a monthly salary, a tax percentage, and monthly expenses. Calculate the annual salary before tax, the annual tax amount, the annual income after tax, and the amount you can save in a year after expenses.

**Input:**
```
monthlySalary = 100000.0
taxPercent = 13.0
monthlyExpenses = 40000.0
```

**Output:**
```
grossYear = 1200000.0
taxYear = 156000.0
netYear = 1044000.0
savings = 564000.0
```

---

## 6. RGB Color Mixer

A color is defined by three numbers: `red`, `green`, `blue` (from 0 to 255). Combine them into a single number in the `0xRRGGBB` format using bit shifts `<<` and `|`.

**Input:**
```
red = 255
green = 128
blue = 0
```

**Output:**
```
0xFF8000
```

---

## 7. Array Statistics

You are given an array of scores. Calculate the sum, the average, the first and last elements, and the length of the array. Access elements by index; do not use loops yet.

**Input:**
```
int[] scores = {78, 92, 65, 88, 97};
```

**Output:**
```
sum = 420
average = 84.0
first = 78
last = 97
length = 5
```

---

## 8. Swap Without Losing Data

You are given two variables. Swap their values using a third variable `temp`. Then solve the same problem without a third variable, using arithmetic.

**Input:**
```
a = 15
b = 42
```

**Output:**
```
a = 42
b = 15
```

---

## 9. Number Decomposer

You are given a positive three-digit number. Calculate the hundreds, tens, and units digits, then rebuild the original number from these digits.

**Input:**
```
number = 583
```

**Output:**
```
hundreds = 5
tens = 8
units = 3
rebuilt = 583
```

---

## 10. Student Report Generator

You are given a first name, a last name, a group number, and three grades. Calculate the average grade and print a report using `String` and `System.out.printf()`.

**Input:**
```
firstName = "Alex"
lastName = "Johnson"
group = "SE-3"
grades = {85, 92, 78}
```

**Output:**
```
===== STUDENT REPORT =====
Name: Alex Johnson
Group: SE-3
Grades: 85, 92, 78
Average: 85.00
==========================
```
