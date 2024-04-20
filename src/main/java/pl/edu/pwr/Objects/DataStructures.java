package pl.edu.pwr.Objects;

public enum DataStructures {
    ArrayList(1),

    LinkedList(2),
    TreeSet(3),
    HashSet(4),
    ArrayDeque(5);

    private final int choice;

    DataStructures(int choice) {
        this.choice = choice;
    }
}
