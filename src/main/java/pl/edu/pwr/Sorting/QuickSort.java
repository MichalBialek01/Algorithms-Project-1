package pl.edu.pwr.Sorting;

import pl.edu.pwr.Objects.Film;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class QuickSort {

    public static void quickSortByRating(List<Film> films) {
        if (films == null || films.isEmpty()) {
            return;
        }
        doSort(films, 0, films.size() - 1, Film.BY_RATING);
    }

    public static void quickSortByTitle(List<Film> films) {
        if (films == null || films.isEmpty()) {
            return;
        }
        doSort(films, 0, films.size() - 1, Film.BY_TITLE);
    }

    private static void doSort(List<Film> array, int left, int right, Comparator<Film> comparator) {
        if (left < right) {
            int pivotIndex = randomPartition(array, left, right, comparator);
            doSort(array, left, pivotIndex - 1, comparator);
            doSort(array, pivotIndex, right, comparator);
        }
    }

    private static int randomPartition(List<Film> array, int left, int right, Comparator<Film> comparator) {
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        Collections.swap(array, randomIndex, right);
        return partition(array, left, right, comparator);
    }

    private static int partition(List<Film> array, int left, int right, Comparator<Film> comparator) {
        Film pivot = array.get((left + right) >>> 1);
        while (left <= right) {
            while (comparator.compare(array.get(left), pivot) < 0) {
                left++;
            }
            while (comparator.compare(pivot, array.get(right)) < 0) {
                right--;
            }
            if (left <= right) {
                Collections.swap(array, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    public static <T extends Comparable<T>> void flip(T[] array, int left, int right) {
        while (left <= right) {
            swap(array, left++, right--);
        }
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}