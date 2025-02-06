import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // --------111--------
        System.out.println("Введите строку: ");
        String s = sc.nextLine();
        String res1 = task1(s);
        System.out.println("Уникальная подстрока: " + res1);

        // --------222--------
        System.out.println();
        int[] arr1 = {9, 8, 6, 7, 90};
        int[] arr2 = {7, 15, 16, 100};
        int[] res2 = task2(arr1, arr2);
        System.out.println("Обьединенный отсортированный массив: " + Arrays.toString(res2));

        // --------333--------
        System.out.println();
        System.out.println("Максимальная сумма подмассива: " + task3(arr1));

        // --------444--------
        int[][] mat = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] res4 = task4(mat);
        System.out.print("\nПовернутая матрица на 90 по часовой\n");
        for (int[] row : res4) {
            for (int el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
        // --------555--------
        System.out.println();
        int target = 14;
        System.out.println("Искомая пара элементов: " + Arrays.toString(task5(arr1, target)));

        // --------666--------
        System.out.println();
        System.out.println("Сумма элементов матрицы: " + task6(mat));

        // --------777--------
        System.out.println();
        System.out.println("Максимальные элементы в каждой строке: " + Arrays.toString(task7(mat)));

        // --------888--------
        int[][] res8 = task8(mat);
        System.out.print("\nПовернутая матрица на 90 против часовой\n");
        for (int[] row : res8) {
            for (int el : row) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    public static String task1(String str) {
        String sub = "";
        int len = str.length();
        int maxLen = 0;
        int start = 0;
        int[] lastIdx = new int[256]; // храним последний индекс символа

        for (int end = 0; end < len; end++) {
            char curChar = str.charAt(end);
            start = Math.max(start, lastIdx[curChar]);
            // длина текущей уникальной подстроки
            int length = end - start + 1;
            if (length > maxLen) {
                maxLen = length;
                sub = str.substring(start, end + 1);
            }
            // Обновляем последний индекс символа
            lastIdx[curChar] = end + 1;
        }

        return sub;
    }

    public static int[] task2(int[] arr1, int[] arr2) {
        int[] finArr = new int[arr1.length + arr2.length];

        int idx = 0;
        for (int i = 0; i < arr1.length; i++) {
            finArr[idx] = arr1[i];
            idx++;
        }
        for (int i = 0; i < arr2.length; i++) {
            finArr[idx] = arr2[i];
            idx++;
        }
        Arrays.sort(finArr);
        return finArr;
    }

    public static int task3(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        int maxCurSum = arr[0];
        int maxSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxCurSum = Math.max(arr[i], maxCurSum + arr[i]);
            maxSum = Math.max(maxSum, maxCurSum);
        }

        return maxSum;
    }

    public static int[][] task4(int[][] mat) {
        int n = mat.length;
        int[][] rotMat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotMat[j][n - 1 - i] = mat[i][j];
            }
        }
        return rotMat;
    }

    public static int[] task5(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[]{arr[i], arr[j]};
                }
            }
        }
        return new int[]{};
    }

    public static int task6(int[][] mat) {
        int sum = 0;
        for (int[] row : mat) {
            for (int el : row) {
                sum += el;
            }
        }
        return sum;
    }

    public static int[] task7(int[][] mat) {
        int[] maxEl = new int[mat.length];
        for (int i = 0; i < mat.length; i++) {
            int max = mat[i][0];
            for (int j = 1; j < mat[i].length; j++) {
                if (mat[i][j] > max) {
                    max = mat[i][j];
                }
            }
            maxEl[i] = max;
        }
        return maxEl;
    }

    public static int[][] task8(int[][] mat) {
        int n = mat.length;
        int[][] rotMat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotMat[n - 1 - j][i] = mat[i][j];
            }
        }
        return rotMat;
    }
}
