/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectUAS;

/**
 *
 * @author Rizki
 */
import java.util.Arrays;

public class SimpleTimSort {

    private static long comparisons = 0;
    private static long moves = 0;
    private static final int RUN = 32;

    public static void sort(int[] a) {
        comparisons = 0;
        moves = 0;
        int n = a.length;

        for (int i = 0; i < n; i += RUN) {
            int end = Math.min((i + RUN), n);
            insertionSort(a, i, end);
        }

        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min((left + size), n);
                int right = Math.min((left + 2 * size), n);

                if (mid < right) {
                    merge(a, left, mid, right);
                }
            }
        }
    }

    private static void insertionSort(int[] a, int left, int right) {
        for (int i = left + 1; i < right; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= left) {
                comparisons++;
                if (a[j] > key) {
                    a[j + 1] = a[j];
                    moves++;
                    j--;
                } else {
                    break;
                }
            }
            a[j + 1] = key;
        }
    }

    private static void merge(int[] a, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(a, left, mid);
        int[] rightArr = Arrays.copyOfRange(a, mid, right);

        int i = 0, j = 0, k = left;

        while (i < leftArr.length && j < rightArr.length) {
            comparisons++;
            if (leftArr[i] <= rightArr[j]) {
                a[k++] = leftArr[i++];
            } else {
                a[k++] = rightArr[j++];
            }
            moves++;
        }
        while (i < leftArr.length) {
            a[k++] = leftArr[i++];
            moves++;
        }
        while (j < rightArr.length) {
            a[k++] = rightArr[j++];
            moves++;
        }
    }

    public static long getComparisons() {
        return comparisons;
    }

    public static long getMoves() {
        return moves;
    }
}
