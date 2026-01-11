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

public class MergeSort {

    private static long comparisons = 0;
    private static long moves = 0;

    public static void sort(int[] a) {
        comparisons = 0;
        moves = 0;
        if (a.length <= 1) {
            return;
        }
        recursiveSort(a);
    }

    private static void recursiveSort(int[] a) {
        if (a.length <= 1) {
            return;
        }

        int mid = a.length / 2;
        int[] left = Arrays.copyOfRange(a, 0, mid);
        int[] right = Arrays.copyOfRange(a, mid, a.length);

        recursiveSort(left);
        recursiveSort(right);

        merge(a, left, right);
    }

    private static void merge(int[] a, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            comparisons++;
            if (left[i] <= right[j]) {
                a[k++] = left[i++];
            } else {
                a[k++] = right[j++];
            }
            moves++;
        }

        while (i < left.length) {
            a[k++] = left[i++];
            moves++;
        }

        while (j < right.length) {
            a[k++] = right[j++];
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
