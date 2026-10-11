import java.util.Objects;

/**
 * Represents a 24-hour clock that stores an hour, minute, and second.
 * Clocks can be advanced, compared for equality, and ordered by time.
 *
 * @author Ryan Kelly
 * @version Autumn 2026
 */
public class Clock implements Comparable<Clock> {

    /** The number of hours in a day. */
    private static final int HOURS_PER_DAY = 24;

    /** The number of minutes in an hour. */
    private static final int MINUTES_PER_HOUR = 60;

    /** The number of seconds in a minute. */
    private static final int SECONDS_PER_MINUTE = 60;

    /** The hour used by the default constructor. */
    private static final int DEFAULT_HOUR = 23;

    /** The minute used by the default constructor. */
    private static final int DEFAULT_MINUTE = 58;

    /** The second used by the default constructor. */
    private static final int DEFAULT_SECOND = 0;

    /** The hour of this clock, in the range 0 through 23. */
    private int myHour;

    /** The minute of this clock, in the range 0 through 59. */
    private int myMinute;

    /** The second of this clock, in the range 0 through 59. */
    private int mySecond;

    /**
     * Constructs a clock set to the given hour, minute, and second.
     *
     * @param theHour the hour, 0 through 23
     * @param theMinute the minute, 0 through 59
     * @param theSecond the second, 0 through 59
     * @throws IllegalArgumentException if any value is outside its valid range
     */
    public Clock(final int theHour, final int theMinute, final int theSecond) {
        setHour(theHour);
        setMinute(theMinute);
        setSecond(theSecond);
    }

    /**
     * Constructs a clock set to the default time of 23:58:00
     * (two minutes to midnight).
     */
    public Clock() {
        this(DEFAULT_HOUR, DEFAULT_MINUTE, DEFAULT_SECOND);
    }

    /**
     * Returns the hour of this clock.
     *
     * @return the hour, 0 through 23
     */
    public int getHour() {
        return myHour;
    }

    /**
     * Returns the minute of this clock.
     *
     * @return the minute, 0 through 59
     */
    public int getMinute() {
        return myMinute;
    }

    /**
     * Returns the second of this clock.
     *
     * @return the second, 0 through 59
     */
    public int getSecond() {
        return mySecond;
    }

    /**
     * Sets the hour of this clock.
     *
     * @param theHour the new hour, 0 through 23
     * @throws IllegalArgumentException if the hour is outside 0 through 23
     */
    public void setHour(final int theHour) {
        if (0 <= theHour && theHour < HOURS_PER_DAY) {
            myHour = theHour;
        } else {
            throw new IllegalArgumentException("Hours must be between 0 and 23.");
        }
    }

    /**
     * Sets the minute of this clock.
     *
     * @param theMinute the new minute, 0 through 59
     * @throws IllegalArgumentException if the minute is outside 0 through 59
     */
    public void setMinute(final int theMinute) {
        if (0 <= theMinute && theMinute < MINUTES_PER_HOUR) {
            myMinute = theMinute;
        } else {
            throw new IllegalArgumentException("Minutes must be between 0 and 59.");
        }
    }

    /**
     * Sets the second of this clock.
     *
     * @param theSecond the new second, 0 through 59
     * @throws IllegalArgumentException if the second is outside 0 through 59
     */
    public void setSecond(final int theSecond) {
        if (0 <= theSecond && theSecond < SECONDS_PER_MINUTE) {
            mySecond = theSecond;
        } else {
            throw new IllegalArgumentException("Seconds must be between 0 and 59.");
        }
    }

    /**
     * Advances the hour by the given amount, wrapping around midnight so the
     * resulting hour stays in the range 0 through 23.
     *
     * @param theAmount the number of hours to advance, 0 or greater
     * @throws IllegalArgumentException if the amount is negative
     */
    public void advanceHour(final int theAmount) {
        if (theAmount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        setHour((getHour() + theAmount) % HOURS_PER_DAY);
    }

    /**
     * Advances the minute by the given amount, carrying into the hour as
     * needed so the resulting minute stays in the range 0 through 59.
     *
     * @param theAmount the number of minutes to advance, 0 or greater
     * @throws IllegalArgumentException if the amount is negative
     */
    public void advanceMinute(final int theAmount) {
        if (theAmount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        int min = getMinute() + theAmount;
        if (min >= MINUTES_PER_HOUR) {
            advanceHour(min / MINUTES_PER_HOUR);
            min = min % MINUTES_PER_HOUR;
        }
        setMinute(min);
    }

    /**
     * Returns this clock's time as a string in the form h:m:s (e.g. 9:49:59).
     *
     * @return the time as a string
     */
    @Override
    public String toString() {
        return myHour + ":" + myMinute + ":" + mySecond;
    }

    /**
     * Compares this clock to another object for equality. Two clocks are
     * equal when they represent the same hour, minute, and second.
     *
     * @param theObject the object to compare against
     * @return true if the object is a Clock with the same time, false otherwise
     */
    @Override
    public boolean equals(final Object theObject) {
        return theObject instanceof Clock && compareTo((Clock) theObject) == 0;
    }

    /**
     * Returns a hash code consistent with {@link #equals(Object)}.
     *
     * @return the hash code for this clock
     */
    @Override
    public int hashCode() {
        return Objects.hash(myHour, myMinute, mySecond);
    }

    /**
     * Compares this clock to another clock for order, earliest time first.
     * Each clock is converted to a count of seconds since midnight and the
     * two counts are compared.
     *
     * @param theOtherClock the clock to compare against
     * @return a negative value if this clock is earlier, a positive value if
     *         it is later, and 0 if the two clocks show the same time
     */
    @Override
    public int compareTo(final Clock theOtherClock) {
        int compare;

        final int thisSeconds = myHour * MINUTES_PER_HOUR * SECONDS_PER_MINUTE
                + myMinute * SECONDS_PER_MINUTE + mySecond;
        final int otherSeconds = theOtherClock.myHour * MINUTES_PER_HOUR * SECONDS_PER_MINUTE
                + theOtherClock.myMinute * SECONDS_PER_MINUTE
                + theOtherClock.mySecond;

        if (thisSeconds == otherSeconds) {
            compare = 0;
        } else if (otherSeconds < thisSeconds) {
            compare = 1;
        } else {
            compare = -1;
        }
        return compare;
    }
}