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
import java.util.Random;
import java.util.Scanner;

public class SortingBenchmark {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int[] BENCHMARK_SIZES = {10, 1000, 10000};

    public static void main(String[] args) {
        while (true) {
            printBanner();
            System.out.println(" [1] Tes Manual (Satu Algoritma)");
            System.out.println(" [2] Benchmark (Bandingkan Semua)");
            System.out.println(" [0] Keluar");
            System.out.print("\n >> Pilih menu: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> handleManualMenu();
                case "2" -> handleBenchmarkMenu();
                case "0" -> {
                    System.out.println("Keluar dari program...");
                    return;
                }
                default -> {
                    System.out.println("Input tidak valid!");
                    pressEnterToContinue();
                }
            }
        }
    }

    private static void handleManualMenu() {
        System.out.println("\n--- PILIH ALGORITMA ---");
        System.out.println(" 1. Insertion Sort");
        System.out.println(" 2. Merge Sort");
        System.out.println(" 3. Simple Tim Sort");
        System.out.print(" >> Pilihan: ");
        String algoChoice = scanner.nextLine();

        String algoName = "";
        switch (algoChoice) {
            case "1" -> algoName = "Insertion Sort";
            case "2" -> algoName = "Merge Sort";
            case "3" -> algoName = "Simple Tim Sort";
            default -> {
                System.out.println("Algoritma tidak valid."); pressEnterToContinue(); return;
            }
        }

        System.out.println("\n--- Test algoritma (" + algoName + ") ---");
        
        System.out.println(" 1. Buat Data Acak (10 / 1.000 / 10.000)");
        System.out.println(" 2. Input Manual (cth: [20, 10, 50...])");
        System.out.print(" >> Pilihan: ");
        String dataChoice = scanner.nextLine();

        int[] data = null;

        switch (dataChoice) {
            case "1" -> {
                System.out.print(" >> Masukkan jumlah data (10/1000/10000): ");
                try {
                    String inputSize = scanner.nextLine();
                    if (inputSize.isEmpty()) return;
                    int size = Integer.parseInt(inputSize);
                    data = generateRandomData(size);
                } catch (NumberFormatException e) {
                    System.out.println("Input ukuran harus berupa angka.");
                    return;
                }
            }
            case "2" -> data = inputManualData();
            default -> {
                System.out.println("Pilihan data tidak valid.");
                return;
            }
        }

        if (data != null) {
            runSingleTest(algoChoice, data);
        }
    }

    private static void runSingleTest(String algoId, int[] data) {
        int[] workData = data.clone();
        
        System.out.println("\n[DATA AWAL]: " + getPreview(workData));
        
        long startTime = System.nanoTime();
        long comps = 0, moves = 0;

        switch (algoId) {
            case "1" -> {
                InsertionSort.sort(workData);
                comps = InsertionSort.getComparisons(); 
                moves = InsertionSort.getMoves();
            }
            case "2" -> {
                MergeSort.sort(workData);
                comps = MergeSort.getComparisons(); 
                moves = MergeSort.getMoves();
            }
            case "3" -> {
                SimpleTimSort.sort(workData);
                comps = SimpleTimSort.getComparisons(); 
                moves = SimpleTimSort.getMoves();
            }
        }
        
        long endTime = System.nanoTime();
        double durationMs = (endTime - startTime) / 1_000_000.0;

        System.out.println("[DATA AKHIR]: " + getPreview(workData));
        
        System.out.println("\n-------------------------------------------------");
        System.out.printf("| %-15s | %27s |%n", "METRIC", "RESULT");
        System.out.println("-------------------------------------------------");
        System.out.printf("| %-15s | %24.4f ms |%n", "Execution Time", durationMs);
        System.out.printf("| %-15s | %27d |%n", "Comparisons", comps);
        System.out.printf("| %-15s | %27d |%n", "Moves", moves);
        System.out.println("-------------------------------------------------");
        
        pressEnterToContinue();
    }

    private static void handleBenchmarkMenu() {
        System.out.println("\n=========================================================================================");
        System.out.println("                                  BENCHMARK SORTING                                      ");
        System.out.println("=========================================================================================");

        for (int n : BENCHMARK_SIZES) {
            System.out.println("\n>> JUMLAH DATA: " + n);
            
            int[] randomData = generateRandomData(n);
            int[] sortedData = generateSortedData(n);
            int[] reverseData = generateReverseData(n);

            printTableSeparator();
            System.out.printf("| %-20s | %-15s | %-13s | %-12s | %-12s |%n", 
                    "SCENARIO", "ALGORITHM", "TIME(ms)", "COMPARISONS", "MOVES");
            printTableSeparator();

            runSuiteRow("Best Case (Sorted)", sortedData);
            printTableSeparator();
            runSuiteRow("Avg Case (Random)", randomData);
            printTableSeparator();
            runSuiteRow("Worst Case (Reverse)", reverseData);
            printTableSeparator();
        }
        pressEnterToContinue();
    }

    private static void runSuiteRow(String scenario, int[] originalData) {
        int[] d1 = originalData.clone();
        long s1 = System.nanoTime();
        InsertionSort.sort(d1);
        double t1 = (System.nanoTime() - s1) / 1_000_000.0;
        printTableRow(scenario, "InsertionSort", t1, InsertionSort.getComparisons(), InsertionSort.getMoves());

        int[] d2 = originalData.clone();
        long s2 = System.nanoTime();
        MergeSort.sort(d2);
        double t2 = (System.nanoTime() - s2) / 1_000_000.0;
        printTableRow("", "MergeSort", t2, MergeSort.getComparisons(), MergeSort.getMoves());

        int[] d3 = originalData.clone();
        long s3 = System.nanoTime();
        SimpleTimSort.sort(d3);
        double t3 = (System.nanoTime() - s3) / 1_000_000.0;
        printTableRow("", "SimpleTimSort", t3, SimpleTimSort.getComparisons(), SimpleTimSort.getMoves());
    }

    private static int[] inputManualData() {
        System.out.println("Masukkan data array (pisahkan dengan spasi atau koma):");
        System.out.println("Contoh: 10, 20, 5, 100 atau [10, 20, 5, 100]");
        System.out.print(" >> Input: ");
        String raw = scanner.nextLine();
        
        raw = raw.replace("[", "").replace("]", "").replace(",", " ");
        String[] parts = raw.trim().split("\\s+");
        
        try {
            int[] arr = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                arr[i] = Integer.parseInt(parts[i]);
            }
            return arr;
        } catch (NumberFormatException e) {
            System.out.println("Format data salah!");
            return null;
        }
    }

    private static void printBanner() {
        System.out.println("\n");
        System.out.println("==========================================================");
        System.out.println("|                  BENCHMARK ALGORITMA                   |");
        System.out.println("|     (Insertion Sort | Merge Sort | Simple Tim Sort)    |");
        System.out.println("==========================================================");
    }

    private static void printTableSeparator() {
        System.out.println("+----------------------+-----------------+---------------+--------------+--------------+");
    }

    private static void printTableRow(String col1, String col2, double col3, long col4, long col5) {
        System.out.printf("| %-20s | %-15s | %10.4f ms | %12d | %12d |%n", col1, col2, col3, col4, col5);
    }

    private static String getPreview(int[] data) {
        if (data.length <= 10) return Arrays.toString(data);
        return String.format("[%d, %d, %d ... %d, %d]", 
            data[0], data[1], data[2], data[data.length-2], data[data.length-1]);
    }

    private static void pressEnterToContinue() {
        System.out.println("\nTekan ENTER untuk kembali ke menu...");
        scanner.nextLine();
    }

    private static int[] generateRandomData(int n) {
        Random rd = new Random();
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = rd.nextInt(100_000);
        return arr;
    }

    private static int[] generateSortedData(int n) {
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = i;
        return arr;
    }

    private static int[] generateReverseData(int n) {
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = n - i;
        return arr;
    }
}