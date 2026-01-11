# Analisis Perbandingan Algoritma Sorting (Insertion, Merge, & Tim Sort)

Repository ini berisi implementasi dan analisis perbandingan kinerja (benchmarking) antara tiga algoritma sorting menggunakan bahasa pemrograman Java. Projek ini dibuat untuk memenuhi tugas **Ujian Akhir Semester (UAS)** mata kuliah Algoritma dan Kompleksitas.

## 📋 Deskripsi
Aplikasi ini melakukan pengujian terhadap tiga algoritma pengurutan:
1.  **Insertion Sort**: Algoritma sederhana yang efektif untuk data kecil.
2.  **Merge Sort**: Algoritma *Divide and Conquer* yang stabil.
3.  **Simple Tim Sort**: Implementasi sederhana dari TimSort (Hybrid antara Insertion & Merge Sort).

Program akan mengukur tiga metrik utama:
* **Waktu Eksekusi** (Execution Time dalam milidetik).
* **Jumlah Perbandingan** (Comparisons).
* **Jumlah Perpindahan/Pergeseran** (Moves/Swaps).

## 🚀 Fitur Aplikasi
Aplikasi berbasis CLI (Command Line Interface) ini memiliki dua mode utama:

### 1. Tes Manual (Single Test)
* Memilih satu algoritma tertentu.
* Bisa menggunakan **Data Acak** (Random) dengan ukuran yang ditentukan user.
* Bisa menggunakan **Input Manual** (User memasukkan angka sendiri).
* Menampilkan data sebelum dan sesudah diurutkan beserta statistik kinerjanya.

### 2. Benchmark Suite (Compare All)
* Menjalankan ketiga algoritma secara otomatis.
* Menguji pada variasi data: **10, 1.000, dan 10.000** elemen.
* Menguji pada tiga skenario kasus:
    * **Best Case:** Data sudah terurut (Sorted).
    * **Average Case:** Data acak (Random).
    * **Worst Case:** Data terurut terbalik (Reverse).
* Output disajikan dalam bentuk tabel.

## 🛠️ Teknologi & Tools
* **Bahasa Pemrograman**: Java (JDK 8)
* **IDE**: Apache NetBeans
* **Tipe Project**: Console/CLI

## 📂 Struktur File
```text
/ProjectUAS
    ├── InsertionSort.java
    ├── MergeSort.java
    ├── SimpleTimSort.java
    └── SortingBenchmark.java
