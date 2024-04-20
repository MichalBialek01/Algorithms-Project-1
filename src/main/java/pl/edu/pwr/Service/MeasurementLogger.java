package pl.edu.pwr.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MeasurementLogger {
    private static int iteration = 1;

    public static void log(long duration, int algorithm, int sortBy, int size) {
        // Nazwy algorytmu i sposobu sortowania
        String algorithmName = "";
        String sortingType = "";

        // Ustalanie nazwy algorytmu na podstawie wartości `algorithm`
        if (algorithm == 1) {
            algorithmName = "MergeSort";
        } else if (algorithm == 2) {
            algorithmName = "QuickSort";
        } else if (algorithm == 3) {
            algorithmName = "BucketSort";
        }

        // Ustalanie typu sortowania na podstawie wartości `sortBy`
        if (sortBy == 1) {
            sortingType = "by Rating";
        } else if (sortBy == 2) {
            sortingType = "by Title";
        }

        // Lokalizacja pliku CSV
        String filePath = "C:\\Users\\DELL\\Desktop\\FilanProject\\ProjektSortowanie\\src\\measurement.csv"; // Ustal ścieżkę do pliku

        // Sprawdzenie, czy plik istnieje, i otworzenie go w trybie do dopisywania
        File file = new File(filePath);
        boolean append = file.exists();

        // Tworzenie/zapisywanie do pliku CSV
        try (FileWriter writer = new FileWriter(file, append)) {
            if (!append) {
                // Jeśli plik nie istnieje, zapisujemy nagłówek
                writer.append("Iteration,Algorithm,SortingType,Size,Duration\n");
            }
            // Zapisywanie danych
            writer.append(String.format("%d,%s,%s,%d,%d\n", iteration++, algorithmName, sortingType, size, duration));
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}


