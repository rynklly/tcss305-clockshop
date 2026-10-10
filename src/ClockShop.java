import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ClockShop {
    private final static String newline = System.lineSeparator();
    private ArrayList<Clock> myClocks;

    public ClockShop(){
        myClocks = new ArrayList<>();
    }

    @Override
    public String toString() {
        int i=0;
        StringBuilder out = new StringBuilder();

        while (i<myClocks.size()){
            out.append(myClocks.get(i).toString());
            out.append(newline);
            i++;
        }

        return out.toString();
    }

    public void addClock(Clock clock) {

        myClocks.add(clock);
    }

    public void sortClocks() {
    }

    public Clock getClock(int i) {
        if(0 > i || i >= myClocks.size()){
            throw new IllegalArgumentException("Index out of range.");
        }
        return myClocks.get(i);
    }

    public void writeClocksToFile(String fileName) {
    }

    public int findClock(Clock clock) {
        int i = 0;
        int result = -1;

        while (i < myClocks.size()){
            if (myClocks.get(i).equals(clock)){
                result = i;
                break;
            }
            i++;
        }
        return result;
    }

    public void setClock(Clock clock, int i) {
    }

    public void fillClockShop(String file) {
        int hour;
        int min;
        int sec;


        try (Scanner in = new Scanner(new File(file))) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (line.isEmpty()) {
                    continue;
                }

                try(Scanner lineScanner = new Scanner(line)){
                    lineScanner.useDelimiter(":");
                    hour = lineScanner.nextInt();
                    min = lineScanner.nextInt();
                    sec = lineScanner.nextInt();

                    addClock(new Clock(hour, min, sec));
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not open file: " + e.getMessage());
        }
    }
}
