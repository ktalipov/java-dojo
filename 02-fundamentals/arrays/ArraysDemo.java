import java.util.Arrays;

public class ArraysDemo {

  public static void main(String[] args) {

    // 1. Array declaration and initialization
    int[] numbers = { 10, 20, 30, 40, 50 };

    System.out.println("=== 1. Basic Array ===");
    System.out.println("Array: " + Arrays.toString(numbers));
    System.out.println("Length: " + numbers.length);
    System.out.println("First element: " + numbers[0]);
    System.out.println("Last element: " + numbers[numbers.length - 1]);

    // 2. Default values
    int[] defaultNumbers = new int[3];
    String[] defaultStrings = new String[3];

    System.out.println("\n=== 2. Default Values ===");
    System.out.println(Arrays.toString(defaultNumbers));
    System.out.println(Arrays.toString(defaultStrings));

    // 3. Modifying array elements
    numbers[0] = 100;
    numbers[1] = 200;

    System.out.println("\n=== 3. Modify Elements ===");
    System.out.println(Arrays.toString(numbers));

    // 4. Classic for loop
    System.out.println("\n=== 4. Classic For Loop ===");

    for (int i = 0; i < numbers.length; i++) {
      System.out.println("Index " + i + ": " + numbers[i]);
    }

    // 5. Enhanced for loop
    System.out.println("\n=== 5. For-Each Loop ===");

    for (int number : numbers) {
      System.out.println(number);
    }

    // 6. Sum and average using loops
    System.out.println("\n=== 6. Sum and Average ===");

    int sum = 0;

    for (int number : numbers) {
      sum += number;
    }

    double average = (double) sum / numbers.length;

    System.out.println("Sum: " + sum);
    System.out.println("Average: " + average);

    // 7. Working with Arrays utility methods
    System.out.println("\n=== 7. Arrays Utility Methods ===");

    int[] values = { 5, 2, 9, 1, 7 };

    Arrays.sort(values);
    System.out.println("Sorted: " + Arrays.toString(values));

    int index = Arrays.binarySearch(values, 7);
    System.out.println("Index of 7: " + index);

    Arrays.fill(values, 0);
    System.out.println("Filled: " + Arrays.toString(values));

    // 8. Copying arrays
    System.out.println("\n=== 8. Copying Arrays ===");

    int[] original = { 1, 2, 3 };
    int[] reference = original;
    int[] copy = original.clone();
    int[] extendedCopy = Arrays.copyOf(original, 5);

    reference[0] = 99;

    System.out.println("Original: " + Arrays.toString(original));
    System.out.println("Reference: " + Arrays.toString(reference));
    System.out.println("Clone: " + Arrays.toString(copy));
    System.out.println("Extended copy: " + Arrays.toString(extendedCopy));

    // 9. Passing arrays to methods
    System.out.println("\n=== 9. Methods and Arrays ===");

    int[] data = { 10, 20, 30 };

    modifyElement(data);
    System.out.println("After modification: " + Arrays.toString(data));

    reassignArray(data);
    System.out.println("After reassignment: " + Arrays.toString(data));

    // 10. Multidimensional arrays
    System.out.println("\n=== 10. Two-Dimensional Array ===");

    int[][] matrix = {
        { 1, 2, 3 },
        { 4, 5, 6 }
    };

    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[row].length; col++) {
        System.out.print(matrix[row][col] + " ");
      }
      System.out.println();
    }

    System.out.println("Deep representation: "
        + Arrays.deepToString(matrix));

    // 11. Jagged arrays
    System.out.println("\n=== 11. Jagged Array ===");

    int[][] jagged = {
        { 1 },
        { 2, 3 },
        { 4, 5, 6 }
    };

    System.out.println(Arrays.deepToString(jagged));

    // 12. Varargs
    System.out.println("\n=== 12. Varargs ===");

    System.out.println("Sum: " + sumNumbers(10, 20, 30));
    System.out.println("Sum: " + sumNumbers(1, 2, 3, 4, 5));

    // 13. Comparing arrays
    System.out.println("\n=== 13. Comparing Arrays ===");

    int[] first = { 1, 2, 3 };
    int[] second = { 1, 2, 3 };

    System.out.println("Same reference: " + (first == second));
    System.out.println("Same contents: " + Arrays.equals(first, second));

    // 14. Array covariance
    System.out.println("\n=== 14. Array Covariance ===");

    Number[] numericValues = new Integer[3];
    numericValues[0] = 10;
    numericValues[1] = 20;

    System.out.println(Arrays.toString(numericValues));

    // 15. Streams
    System.out.println("\n=== 15. Streams ===");

    int[] streamNumbers = { 10, 20, 30, 40 };

    int streamSum = Arrays.stream(streamNumbers).sum();
    double streamAverage = Arrays.stream(streamNumbers)
        .average()
        .orElse(0.0);

    System.out.println("Stream sum: " + streamSum);
    System.out.println("Stream average: " + streamAverage);
  }

  static void modifyElement(int[] arr) {
    arr[0] = 999;
  }

  static void reassignArray(int[] arr) {
    arr = new int[] { 1, 2, 3 };
  }

  static int sumNumbers(int... numbers) {
    int total = 0;

    for (int number : numbers) {
      total += number;
    }

    return total;
  }
}
