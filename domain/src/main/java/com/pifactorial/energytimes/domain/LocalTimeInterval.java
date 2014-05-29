package com.pifactorial.energytimes.domain;

import org.joda.time.Interval;
import org.joda.time.LocalTime;
import org.joda.time.Instant;
import java.util.Locale;

public class LocalTimeInterval {
    private static final Instant CONSTANT = new Instant(0);
    private final LocalTime from;
    private final LocalTime to;

    public LocalTimeInterval(LocalTime from, LocalTime to) {
        this.from = from;
        this.to = to;
    }

    public LocalTimeInterval(int startHour, int startMinute, int endHour, int endMinute) {
        this.from = new LocalTime(startHour, startMinute);
        this.to = new LocalTime(endHour, endMinute);
    }

    public int getStartMinute() {
        return from.getMinuteOfHour();
    }

    public int getStartHour() {
        return from.getHourOfDay();
    }

    public int getEndMinute() {
        return to.getMinuteOfHour();
    }

    public int getEndHour() {
        return to.getHourOfDay();
    }

    public boolean isValid() {
        try {
            return toInterval() != null;
        }
        catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean overlapsWith(LocalTimeInterval timeInterval) {
        return this.toInterval().overlaps(timeInterval.toInterval());
    }

    /**
     * @return this represented as a proper Interval
     * @throws IllegalArgumentException if invalid (to is before from)
     */
    private Interval toInterval() throws IllegalArgumentException {
        return new Interval(from.toDateTime(CONSTANT), to.toDateTime(CONSTANT));
    }

    public static LocalTimeInterval Allday() {
        return new LocalTimeInterval(0,0, 23, 59);
    }

    public boolean overlapsWith(LocalTime l) {
        return overlapsWith(new LocalTimeInterval(l, l.plusMillis(1)));
    }


    public static LocalTimeInterval getMergedTimeInterval(LocalTimeInterval start, LocalTimeInterval end) {
        return new LocalTimeInterval(start.from, end.to);
    }

    public LocalTime getLocalTimeAfterEnd() {
        LocalTime later = to.plusMinutes(1);

        // If we pass midnight do not add minutes
        if(later.isBefore(this.to) || later == LocalTime.MIDNIGHT) {
            return to;
        }
        return later;
    }

    public String toString() {
        return String.format(Locale.US, "From %s --- to %s", from.toString(), to.toString());
    }

    @Override
    public boolean equals(Object other) {
        if ( this == other) return true;
        if ( !(other instanceof LocalTimeInterval ) ) return false;
        LocalTimeInterval that = (LocalTimeInterval) other;

        return this.from.equals(that.from) && this.to.equals(that.to);
    }
}
