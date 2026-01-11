/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ProjectUAS;

/**
 *
 * @author Rizki
 */
public class InsertionSort {

    private static long comparisons = 0;
    private static long moves = 0;

    public static void sort(int[] a) {
        comparisons = 0;
        moves = 0;

        int n = a.length;
        for (int i = 1; i < n; i++) {
            int key = a[i];
            int j = i - 1;

            while (j >= 0) {
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

    public static long getComparisons() {
        return comparisons;
    }

    public static long getMoves() {
        return moves;
    }
}
