package pl.edu.pwr.Sorting;

import pl.edu.pwr.Objects.Film;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BucketSort {

    public static void sortByRating(List<Film> films) {
        int maxRating = films.stream()
                .mapToInt(film -> film.rating.orElse(0))
                .max()
                .orElse(10);
        List<List<Film>> buckets = new ArrayList<>(maxRating + 1);
        for (int i = 0; i <= maxRating; i++) {
            buckets.add(new ArrayList<>());
        }

        for (Film film : films) {
            int bucketIndex = film.rating.orElse(-1);
            if (bucketIndex != -1) {
                buckets.get(bucketIndex).add(film);
            }
        }
        int index = 0;
        for (List<Film> bucket : buckets) {
            Collections.sort(bucket, Comparator.comparing(f -> f.filmTitle));
            for (Film film : bucket) {
                films.set(index++, film);
            }
        }
    }

    public static void sortByTitle(List<Film> films) {
        System.out.println("Funkcjonalność dla tej struktury nie została zaimplementowana, z powodu problemów z implementacją - " +
                "Próba zaimplemetnowania tworzenia kubełkó na podstawie 1 znaku tytułu, nie powiodła się, ponieważ tytuł może zaczynać sie od znaku specjalego");
//        int numberOfBuckets = 32;
//        List<List<Film>> buckets = new ArrayList<>(numberOfBuckets);
//        for (int i = 0; i < numberOfBuckets; i++) {
//            buckets.add(new ArrayList<>());
//        }
//
//        for (Film film : films) {
//            char firstChar = film.filmTitle.toUpperCase().charAt(0);
//            int bucketIndex = -1;
//
//            if (firstChar >= 'A' && firstChar <= 'Z') {
//                bucketIndex = firstChar - 'A' + 1;
//            } else if (film.filmTitle.length() > 1) {
//                char secondChar = film.filmTitle.toUpperCase().charAt(1);
//                if (secondChar >= 'A' && secondChar <= 'Z') {
//                    bucketIndex = secondChar - 'A' + 1;
//                }
//            }
//
//            if (bucketIndex == -1) {
//                bucketIndex = 0;
//            }
//
//            buckets.get(bucketIndex).add(film);
//        }
//        int index = 0;
//        for (List<Film> bucket : buckets) {
//            Collections.sort(bucket, Comparator.comparing(f -> f.filmTitle));
//            for (Film film : bucket) {
//                films.set(index++, film);
//            }
//        }
    }
}

