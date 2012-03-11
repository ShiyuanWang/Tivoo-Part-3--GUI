package processor;

import input.Event;

import java.util.Comparator;

public class SortByStartDate extends Sorters {

    @Override
    protected Comparator<Event> getComparator() {
        return new StartTimeComparator();
    }
}