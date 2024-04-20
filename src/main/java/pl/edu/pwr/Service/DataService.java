package pl.edu.pwr.Service;

import pl.edu.pwr.Objects.Film;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;


public class DataService {
    public static final String csvFilePath = "C:\\Users\\DELL\\Desktop\\ProjektowanieAlgorytmowObiekty\\src\\inputData\\example.csv";

    public ArrayList<Film> generatePreparedArrayList() {
        return new ArrayList<Film>();
    }

    public LinkedList<Film> generateLinkedList() {

        return new LinkedList<Film>();
    }

    public static ArrayList<Film> generatePreparedArrayList(int size, ArrayList<Film> preparedArrayList) {
        // Sprawdzenie, czy żądana liczba elementów nie przekracza rozmiaru oryginalnej listy
        int copySize = Math.min(size, preparedArrayList.size());

        // Utworzenie nowej ArrayList, kopiując określoną liczbę elementów
        return new ArrayList<Film>(preparedArrayList.subList(0, copySize));
    }

    public static LinkedList<Film> generatePreparedLinkedList(int size, LinkedList<Film> preparedLinkedList) {
        int copySize = Math.min(size, preparedLinkedList.size());

        // Utworzenie nowej ArrayList, kopiując określoną liczbę elementów
        return new LinkedList<>(preparedLinkedList.subList(0, copySize));
    }


    public ArrayList<Film> readFromCSV(ArrayList<Film> arrayList) {

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);


                if (values.length >= 2) {

                    int id = Integer.parseInt(values[0]);

                    String filmTitle = values[1].replaceAll("^\"|\"$", "");

                    Optional<Integer> rating = values.length == 3 && !values[2].isEmpty() ?
                            Optional.of((int) Math.round(Double.parseDouble(values[2]))) :
                            Optional.empty();
                    arrayList.add(new Film(id, filmTitle, rating));
                }
            }
            return arrayList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public LinkedList<Film> readFromCSV(LinkedList<Film> linkedList) {

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {


                String[] values = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                int id = Integer.parseInt(values[0]);

                String filmTitle = values[1].replaceAll("^\"|\"$", "");
                Optional<Integer> rating = values.length > 2 && !values[2].isEmpty()
                        ? Optional.of((int) Math.round(Double.parseDouble(values[2].trim()))) // Parsing and rounding the ocenaFilmu as Integer
                        : Optional.empty();
                linkedList.add(new Film(id, filmTitle, rating));
            }
            return linkedList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @Possible - second impl

    public LinkedList<Film> readFromCSV(LinkedList<Film> linkedList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);

                if (parts.length < 2) continue;

                int id = Integer.parseInt(parts[0].trim());
                String title = parts[1].trim();
                Optional<Integer> rating = Optional.empty();

                if (parts.length == 3 && !parts[2].trim().isEmpty()) {
                    try {
                        rating = Optional.of((int) Math.round(Double.parseDouble(parts[2].trim())));
                    } catch (NumberFormatException e) {
                        System.out.println("Removed entry due to invalid rating format: " + line);
                    }
                }

                linkedList.add(new Film(id, title, rating));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from CSV file: " + e.getMessage(), e);
        }
        return linkedList;
    }
     */

}


