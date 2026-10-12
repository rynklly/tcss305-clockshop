import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Holds a collection of Clock objects and provides operations to fill the
 * collection from a file, sort and search it, retrieve and replace clocks,
 * and write the collection to a file.
 *
 * @author Ryan Kelly
 * @version Autumn 2026
 */
public class ClockShop {

    /** The platform-specific line separator. */
    private static final String NEWLINE = System.lineSeparator();

    /** The clocks held by this shop. */
    private ArrayList<Clock> myClocks;

    /**
     * Constructs an empty clock shop.
     */
    public ClockShop() {
        myClocks = new ArrayList<>();
    }

    /**
     * Reads clock times from the given file, one per line in the form
     * hour:minute:second, and adds a Clock for each to this shop.
     *
     * @param theInputFileName the name of the file to read from
     */
    public void fillClockShop(final String theInputFileName) {
        try (Scanner in = new Scanner(new File(theInputFileName))) {
            in.useDelimiter(":|\\R+");
            while (in.hasNextInt()) {
                final int hour = in.nextInt();
                final int min = in.nextInt();
                final int sec = in.nextInt();

                addClock(new Clock(hour, min, sec));
            }
        } catch (final FileNotFoundException e) {
            System.err.println("Could not open file: " + e.getMessage());
        }
    }

    /**
     * Sorts the clocks in ascending order (earliest time first) using
     * insertion sort and the Clock compareTo method.
     */
    public void sortClocks() {
        for (int i = 1; i < myClocks.size(); i++) {
            final Clock current = myClocks.get(i);
            int j = i - 1;

            while (j >= 0 && myClocks.get(j).compareTo(current) > 0) {
                myClocks.set(j + 1, myClocks.get(j));
                j--;
            }

            myClocks.set(j + 1, current);
        }
    }

    /**
     * Searches for the given clock in this shop.
     *
     * @param theClock the clock to search for
     * @return the index of the first matching clock, or -1 if not found
     */
    public int findClock(final Clock theClock) {
        int result = -1;

        for (int i = 0; i < myClocks.size(); i++) {
            if (myClocks.get(i).equals(theClock)) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Returns the clock at the given index.
     *
     * @param theIndex the index of the clock to retrieve
     * @return the clock at that index
     * @throws IllegalArgumentException if the index is out of range
     */
    public Clock getClock(final int theIndex) {
        if (theIndex < 0 || theIndex >= myClocks.size()) {
            throw new IllegalArgumentException("Index out of range.");
        }
        return myClocks.get(theIndex);
    }

    /**
     * Replaces the clock at the given index with the given clock.
     *
     * @param theClock the clock to store
     * @param theIndex the index at which to store it
     * @throws IllegalArgumentException if the index is out of range
     */
    public void setClock(final Clock theClock, final int theIndex) {
        if (theIndex < 0 || theIndex >= myClocks.size()) {
            throw new IllegalArgumentException("Index out of range.");
        }
        myClocks.set(theIndex, theClock);
    }

    /**
     * Writes the clocks in this shop to the given file, one per line,
     * then closes the file.
     *
     * @param theFilename the name of the file to write to
     */
    public void writeClocksToFile(final String theFilename) {
        try (PrintStream out = new PrintStream(new File(theFilename))) {
            out.print(toString());
        } catch (final FileNotFoundException e) {
            System.err.println("Could not open file: " + e.getMessage());
        }
    }

    /**
     * Returns the clocks in this shop as a string, one clock per line,
     * each followed by a newline.
     *
     * @return the clocks as a string
     */
    @Override
    public String toString() {
        final StringBuilder out = new StringBuilder();

        for (final Clock clock : myClocks) {
            out.append(clock);
            out.append(NEWLINE);
        }

        return out.toString();
    }

    /**
     * Adds a clock to the end of this shop.
     *
     * @param theClock the clock to add
     */
    public void addClock(final Clock theClock) {
        myClocks.add(theClock);
    }
}