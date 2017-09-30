package caloriecounter.input;

import java.util.ArrayList;
import java.util.Scanner;

public class CsvInput implements Input<String> {
    private Iterable<String> contents;

    public CsvInput(Scanner scanner) {
        ArrayList<String> list  = new ArrayList<>();
        while(scanner.hasNext()) {
            list.add(scanner.next());
        }
        scanner.close();
        contents = list;
    }

    @Override
    public Iterable<String> getContents() {
        return contents;
    }
}
