public class Clock implements Comparable<Clock> {

    private int myHours;
    private int myMinutes;
    private int mySeconds;
    private final static int defaultHour = 23;
    private final static int defaultMinute = 58;
    private final static int defaultSecond = 0;
    private final static String newline = System.lineSeparator();


    @Override
    public String toString() {
        return myHours + ":" + myMinutes + ":" + mySeconds;
    }

    public Clock(int i, int i1, int i2) {

        setHour(i);
        setMinute(i1);
        setSecond(i2);

    }

    public Clock() {
        setHour(defaultHour);
        setMinute(defaultMinute);
        setSecond(defaultSecond);
    }

    @Override
    public boolean equals(Object clock2) {

        return this.compareTo((Clock) clock2) == 0;
    }

    public int compareTo(Clock clock2) {
        int compare;
        if (clock2.myHours == myHours && clock2.myMinutes == myMinutes && clock2.mySeconds == mySeconds) {
            compare = 0;
        } else if (clock2.myHours < myHours) {
            compare = 1;
        } else if (clock2.myMinutes < myMinutes) {
            compare = 1;
        } else if (clock2.mySeconds < mySeconds){
            compare = 1;
        } else {
            compare = -1;
        }
        return compare;
    }

    public void advanceMinute(int i) {
        int min = getMinute() + i;
        if (min >= 60){
            advanceHour(min/60);
            min = min%60;
        }
        setMinute(min);
    }

    public int getMinute() {
        return myMinutes;
    }

    public void setMinute(int i) {
        if(0 <= i && i <= 59) {
            myMinutes = i;
        } else {
            throw new IllegalArgumentException("Minutes must be between 0 and 59.");
        }
    }

    public int getHour() {
        return myHours;
    }

    public void setHour(int i) {
        if(0<=i && i<=23) {
            myHours = i;
        } else {
            throw new IllegalArgumentException("Hours must be between 0 and 23.");
        }
    }

    public void advanceHour(int i) {
        int hour = getHour() + i;
        if (hour >= 24){
            hour = hour%24;
        }
        setHour(hour);
    }

    public void setSecond(int i) {
        if (0<=i && i<=59) {
            mySeconds = i;
        } else {
            throw new IllegalArgumentException("Seconds must be between 0 and 59");
        }
    }

    public int getSecond() {
        return mySeconds;
    }
}
