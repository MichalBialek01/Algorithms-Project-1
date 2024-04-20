package pl.edu.pwr.Service;

import pl.edu.pwr.Objects.DataStructures;
import pl.edu.pwr.Objects.Film;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Scanner;

public class Comunicator {
    public void welcomeMessage() {
        System.out.println("Witaj w aplikcaji do analizy danych filmowych !");
    }

    public int selectDataStructure() {
        System.out.println("Podaj na jakim typie kolekcji chcesz przetestować algorytm");
        System.out.printf("1 -%s %n", DataStructures.ArrayList);
        System.out.printf("2 -%s %n", DataStructures.LinkedList);
        System.out.print("6 - Zakończ program");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }

    public int askForSizeOfData(Collection<Film> collection) {


        System.out.println("Podaj oczekiwaną ilość elementów w strukurze:");
        System.out.println("1 - 10K");
        System.out.println("2 - 100K");
        System.out.println("3 - 500K");
        System.out.println("4 - 1M ");
        System.out.println("5 - MAX ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                return 10000;
            case 2:
                return 100000;
            case 3:
                return 500000;
            case 4:
                return 1000000;
            case 5:
                return collection.size();
            default:
                System.err.println("Podano złą wartość, wpisz ponownie.");
        }
    return 0;

    }

    public int askForAlghoritm(int strcutureSize) {
        System.out.println("Wybierz jaki algroytm do analizy "+strcutureSize+ " elementowej struktury ?" );
        System.out.println("1 - Merge sort - sortowanie przez scalanie");
        System.out.println("2 - Quicksort - sortowanie szybie");
        System.out.println("3 - Bucket sort  - sortowanie kubełkowe");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }

    public int askForSortingType() {
        System.out.println("Podaj czy sortowanie ma się odbyć po ocenie (1), czy alfabetycznie po tytule(2)?");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        return choice;
    }

    public void printMeasurementOFindingData(long durationOfFindingWrongRecords, long durationOfRemovingWrongRecords, int sizeOfStrcutureBeforeRemobingWrongRecords, int sizeOfStrcutureAfterRemobingWrongRecords) {
        System.out.println("Czas wyszukiwania błędnych elementów: "+durationOfFindingWrongRecords+ "ns" );
        System.out.println("Czas usuwania błędnych elementów: "+ durationOfRemovingWrongRecords+ "ns" );
        System.out.println("Wielkość struktury przed usuwaniem błędnych elementów  "+sizeOfStrcutureBeforeRemobingWrongRecords);
        System.out.println("Wielkość struktury po usunięciu błędnych elementów  "+sizeOfStrcutureAfterRemobingWrongRecords);
        System.out.println("Ilość usuniętych elementów: "+ (sizeOfStrcutureBeforeRemobingWrongRecords-sizeOfStrcutureAfterRemobingWrongRecords));
    }

    public void printMeasurementOfSortingData(long duration, int aloghoritm, int sortBy, int size){


        String alghoritmName="";
        String sortingType="";

        if(aloghoritm==1){
            alghoritmName= "MergeSort";
        }if (aloghoritm==2){
            alghoritmName= "QuickSort";
        }if(aloghoritm==3){
            alghoritmName= "BucketSort";
        }

        if(sortBy==1){
            sortingType="by Rating";
        }if(sortBy==2){
            sortingType="by Title";
        }

        System.out.println("Wybrany algorytm: " + alghoritmName+" na  "+size+ "elementowej strukturze wykonaał sortowanie po: "+ sortBy+ " w czasies "+ duration+ "ns");
        MeasurementLogger.log(duration,aloghoritm,sortBy,size);


    }


    public boolean adkEndOrNextMeasurement() {
        System.out.println("Czy chczesz wykoać nastęny pomiar ? ");
        System.out.println("1 - Tak ");
        System.out.println("2 - Nie ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        if(choice==1){
            return false;
        }
        if(choice==2){
            return true;
        }
        return false;
    }

    public void printStatisticsMeasurement(BigDecimal filmAverage, float filmMedian, int dataStructureSize) {
        System.out.println("Średnia ocena filmu dla: "+dataStructureSize+" elementów wynosi: "+ filmAverage);
        System.out.println("Mediana ocen dla "+dataStructureSize+" elementów wynosi: "+filmMedian);
    }

    public int askForIterations() {
        System.out.println("Podaj ile iteracji chcesz przeprowadzić ?");
        Scanner scanner = new Scanner(System.in);
        int iterations = scanner.nextInt();
        return iterations;
    }
}

