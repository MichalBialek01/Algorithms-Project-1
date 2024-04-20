package pl.edu.pwr.Sorting;

import pl.edu.pwr.Objects.Film;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSort {
    private static ArrayList<Film> aux;

    public static void sortByRating(List<Film> films) {
        aux = new ArrayList<>(films.size());
        for (int i = 0; i < films.size(); i++) {
            aux.add(null);
        }
        doSort(films, 0, films.size() - 1, Comparator.comparing(
                film -> film.getRating().orElse(null),
                Comparator.nullsLast(Comparator.naturalOrder())));
    }

    public static void sortByName(List<Film> films) {
        aux = new ArrayList<>(films.size());
        for (int i = 0; i < films.size(); i++) {
            aux.add(null);
        }
        doSort(films, 0, films.size() - 1, Comparator.comparing(Film::getFilmTitle));
    }

    private static void doSort(List<Film> arr, int left, int right, Comparator<Film> comparator) {
        if (left < right) {
            int mid = (left + right) >>> 1;
            doSort(arr, left, mid, comparator);
            doSort(arr, mid + 1, right, comparator);
            merge(arr, left, mid, right, comparator);
        }
    }

    private static void merge(List<Film> arr, int left, int mid, int right, Comparator<Film> comparator) {
        for (int i = left; i <= right; i++) {
            aux.set(i, arr.get(i));
        }

        int i = left, j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (j > right) {
                arr.set(k, aux.get(i++));
            } else if (i > mid) {
                arr.set(k, aux.get(j++));
            } else {
                Film leftFilm = aux.get(i);
                Film rightFilm = aux.get(j);
                if (comparator.compare(leftFilm, rightFilm) <= 0) {
                    arr.set(k, leftFilm);
                    i++;
                } else {
                    arr.set(k, rightFilm);
                    j++;
                }
            }
        }
    }
}
 