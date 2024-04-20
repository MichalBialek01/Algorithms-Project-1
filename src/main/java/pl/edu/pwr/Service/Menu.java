package pl.edu.pwr.Service;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import pl.edu.pwr.Objects.Film;

import java.util.ArrayList;
import java.util.LinkedList;

@State(Scope.Thread)
public class Menu {
    public static boolean programWorking = true;

    public void run() {


        Comunicator comunicator = new Comunicator();
        DataService dataService = new DataService();
        Measurement measurement = new Measurement();

        comunicator.welcomeMessage();


        int choice = comunicator.selectDataStructure();
        switch (choice) {
            case 1: //ArrayList
                ArrayList<Film> emptyArrayList = dataService.generatePreparedArrayList();
                ArrayList<Film> preparedArrayList = dataService.readFromCSV(emptyArrayList);
                measurement.sortingMeasurements(preparedArrayList, comunicator, dataService);
                Measurement.measureAlghoritms(preparedArrayList, comunicator, dataService);



            case 2://LinkedList
                LinkedList<Film> emptyLinkedList = dataService.generateLinkedList();
                LinkedList<Film> preparedLinkedList = dataService.readFromCSV(emptyLinkedList);
                measurement.sortingMeasurements(preparedLinkedList, comunicator, dataService);
                Measurement.measureAlghoritms(preparedLinkedList, comunicator, dataService);

                break;
            case 6:
                System.out.println("Kończenie programu");
                System.exit(0);
            default:
                throw new RuntimeException("Nie znaleziono podanej struktury");
        }


    }
}
