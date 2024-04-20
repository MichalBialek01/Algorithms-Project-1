package pl.edu.pwr.Service;

import pl.edu.pwr.Objects.Film;
import pl.edu.pwr.Sorting.BucketSort;
import pl.edu.pwr.Sorting.MergeSort;
import pl.edu.pwr.Sorting.QuickSort;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;

public class Measurement {

    public static BigDecimal getFilmAverage(List<Film> preparedArrayList) {
        long sumCounter = 0;
        for (Film film : preparedArrayList) {
            sumCounter += film.getRating().get();
        }
        int structureSize = preparedArrayList.size();
        BigDecimal size = BigDecimal.valueOf(structureSize);
        BigDecimal sum = BigDecimal.valueOf(sumCounter);
        BigDecimal average = sum.divide(size, MathContext.DECIMAL64);
        return average;
    }

    public static float getFilmMedian(List<Film> preparedArrayList) {
        int structureSize = preparedArrayList.size();
        int index = 0;

        if (structureSize % 2 != 0) {
            index = structureSize / 2;
            return preparedArrayList.get(index).getRating().get();
        } else {
            int middleIndex1 = structureSize / 2 - 1;
            int middleIndex2 = structureSize / 2;
            Integer middleRatingElement1 = preparedArrayList.get(middleIndex1).getRating().get();
            Integer middleRatingElement2 = preparedArrayList.get(middleIndex2).getRating().get();
            return (float)(middleRatingElement1 + middleRatingElement2) / 2;
        }
    }

    public void sortingMeasurements(Collection<Film> films, Comunicator comunicator, DataService dataService) {

        if (films instanceof ArrayList<Film>) {
            int sizeBefore = films.size();
            long startMeasure1 = System.nanoTime();
            int falseRecords = getNumberOfFalseRecords(films);
            long endMeasure1 = System.nanoTime();
            long measureDuration1 = endMeasure1 - startMeasure1;

            long startMeasure2 = System.nanoTime();
            Collection<Film> preparedFilms = deleteRecords(films);
            long endMeasure2 = System.nanoTime();
            long measureDuration2 = endMeasure2 - startMeasure1;

            comunicator.printMeasurementOFindingData(measureDuration1,measureDuration2,sizeBefore,preparedFilms.size());
        }
        if (films instanceof LinkedList<Film>) {

            long startMeasure1 = System.nanoTime();
            int falseRecords = getNumberOfFalseRecords(films);
            long endMeasure1 = System.nanoTime();
            long measureDuration1 = endMeasure1 - startMeasure1;

            long startMeasure2 = System.nanoTime();
            Collection<Film> preparedFilms = deleteRecords(films);
            long endMeasure2 = System.nanoTime();
            long measureDuration2 = endMeasure2 - startMeasure1;

            comunicator.printMeasurementOFindingData(measureDuration1, measureDuration2, films.size(), preparedFilms.size());
        }


    }


    private Collection<Film> deleteRecords(Collection<Film> films) {
        Iterator<Film> iterator = films.iterator();
        while (iterator.hasNext()) {
            Film film = iterator.next();
            if (film.getRating().isEmpty()) {
                iterator.remove();
            }
        }
        return films;
    }


    private void printList(Collection<Film> films) {
        for (Film film : films) {
            System.out.println(film.toString());
        }
    }

    private ArrayList<Integer> getIndexesOfFalseRecords(Collection<Film> films) {
        ArrayList<Integer> listOfIndexes = new ArrayList<>();
        for (Film film : films) {
            if (film.getRating().isEmpty()) {
                listOfIndexes.add(film.id);
            }
        }
        return listOfIndexes;
    }

    private int getNumberOfFalseRecords(Collection<Film> films) {
        int counter = 0;
        for (Film film : films) {
            if (film.getRating().isEmpty()) {
                counter++;
            }
        }
        return counter;
    }
        
    public static void measureAlghoritms(LinkedList<Film> preparedLinkedList, Comunicator comunicator, DataService dataService) {

        int size = comunicator.askForSizeOfData(preparedLinkedList);

        LinkedList<Film> sizedLinkedList = DataService.generatePreparedLinkedList(size, preparedLinkedList);
        int choosenAlghoritm = comunicator.askForAlghoritm(size);
        int sortBy = comunicator.askForSortingType();
        int iterations = comunicator.askForIterations();

        switch (choosenAlghoritm) {
            case 1: //Merge sort
                for (int i = 0; i < iterations; i++) {
                    if (sortBy == 1) {
                        long begin = System.nanoTime();
                        MergeSort.sortByRating(sizedLinkedList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        BigDecimal filmAverage = getFilmAverage(sizedLinkedList);
                        float filmMedian = getFilmMedian(sizedLinkedList);
                        comunicator.printMeasurementOfSortingData(duration, choosenAlghoritm, sortBy, sizedLinkedList.size());
                        comunicator.printStatisticsMeasurement(filmAverage, filmMedian, sizedLinkedList.size());
                    }
                    if (sortBy == 2) {
                        long begin = System.nanoTime();
                        MergeSort.sortByName(sizedLinkedList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        comunicator.printMeasurementOfSortingData(duration, choosenAlghoritm, sortBy, sizedLinkedList.size());
                    }
                }

                break;
            case 2: //Quick sort
                for (int i = 0; i < iterations; i++) {

                    if (sortBy == 1) {
                        long begin = System.nanoTime();
                        QuickSort.quickSortByRating(sizedLinkedList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        BigDecimal filmAverage = getFilmAverage(sizedLinkedList);
                        float filmMedian = getFilmMedian(sizedLinkedList);
                        comunicator.printStatisticsMeasurement(filmAverage, filmMedian, sizedLinkedList.size());
                    }
                    if (sortBy == 2) {
                        long begin = System.nanoTime();
                        QuickSort.quickSortByTitle(sizedLinkedList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        getFilmAverage(sizedLinkedList);
                        getFilmMedian(sizedLinkedList);
                        comunicator.printMeasurementOfSortingData(duration, choosenAlghoritm, sortBy, sizedLinkedList.size());
                    }
                }
                break;
            case 3://bucket sort
                for (int i = 0; i < iterations; i++) {
                    if (sortBy == 1) {
                        long begin = System.nanoTime();
                        BucketSort.sortByRating(sizedLinkedList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        BigDecimal filmAverage = getFilmAverage(sizedLinkedList);
                        float filmMedian = getFilmMedian(sizedLinkedList);
                        comunicator.printMeasurementOfSortingData(duration, choosenAlghoritm, sortBy, sizedLinkedList.size());
                        comunicator.printStatisticsMeasurement(filmAverage, filmMedian, sizedLinkedList.size());
                    }
                    if (sortBy == 2) {
                        long begin = System.nanoTime();
                        BucketSort.sortByTitle(sizedLinkedList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        comunicator.printMeasurementOfSortingData(duration, choosenAlghoritm, sortBy, sizedLinkedList.size());
                    }
                }
                break;
            default:
                System.err.println("nie znaleziono takiej opcji ");
                break;
        }
    }

    public static void measureAlghoritms(ArrayList<Film> preparedArrayList, Comunicator comunicator, DataService dataService) {

        int size = comunicator.askForSizeOfData(preparedArrayList);

        ArrayList<Film> sizedArrayList = DataService.generatePreparedArrayList(size, preparedArrayList);
        int choosenAlghoritm = comunicator.askForAlghoritm(size);
        int sortBy = comunicator.askForSortingType();
        int iterations = comunicator.askForIterations();

        switch (choosenAlghoritm) {
            case 1: //Merge sort

                for (int i = 0; i < iterations; i++) {
                    if (sortBy == 1) {
                        long begin = System.nanoTime();
                        MergeSort.sortByRating(sizedArrayList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        BigDecimal filmAverage = getFilmAverage(sizedArrayList);
                        float filmMedian = getFilmMedian(sizedArrayList);
                        comunicator.printMeasurementOfSortingData(duration, choosenAlghoritm, sortBy, sizedArrayList.size());
                        comunicator.printStatisticsMeasurement(filmAverage, filmMedian, sizedArrayList.size());
                    }
                    if (sortBy == 2) {
                        long begin = System.nanoTime();
                        MergeSort.sortByName(sizedArrayList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        comunicator.printMeasurementOfSortingData(begin, choosenAlghoritm, sortBy, sizedArrayList.size());
                    }
                }
                break;
            case 2: //Quick sort
                for (int i = 0; i < iterations; i++) {

                    if (sortBy == 1) {
                        long begin = System.nanoTime();
                        QuickSort.quickSortByRating(sizedArrayList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        BigDecimal filmAverage = getFilmAverage(sizedArrayList);
                        float filmMedian = getFilmMedian(sizedArrayList);
                        comunicator.printMeasurementOfSortingData(duration, choosenAlghoritm, sortBy, sizedArrayList.size());
                        comunicator.printStatisticsMeasurement(filmAverage, filmMedian, sizedArrayList.size());
                    }
                    if (sortBy == 2) {
                        long begin = System.nanoTime();
                        QuickSort.quickSortByTitle(sizedArrayList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        comunicator.printMeasurementOfSortingData(duration, choosenAlghoritm, sortBy, sizedArrayList.size());
                    }
                }
                break;
            case 3://bucket sort
                for (int i = 0; i < iterations; i++) {
                    if (sortBy == 1) {
                        long begin = System.nanoTime();
                        BucketSort.sortByRating(sizedArrayList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        BigDecimal filmAverage = getFilmAverage(sizedArrayList);
                        float filmMedian = getFilmMedian(sizedArrayList);
                        comunicator.printMeasurementOfSortingData(duration, choosenAlghoritm, sortBy, sizedArrayList.size());
                        comunicator.printStatisticsMeasurement(filmAverage, filmMedian, sizedArrayList.size());
                    }
                    if (sortBy == 2) {
                        long begin = System.nanoTime();
                        BucketSort.sortByTitle(sizedArrayList);
                        long end = System.nanoTime();
                        long duration = end - begin;
                        comunicator.printMeasurementOfSortingData(duration, choosenAlghoritm, sortBy, sizedArrayList.size());
                    }
                }
                break;
            default:
                System.err.println("nie znaleziono takiej opcji ");
                break;
        }
    }


}
