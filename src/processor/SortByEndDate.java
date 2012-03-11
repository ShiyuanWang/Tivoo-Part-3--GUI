package processor;

import input.Event;

import java.util.Comparator;

public class SortByEndDate extends Sorters {
    @Override
    protected Comparator<Event> getComparator() {
        return new EndTimeComparator();
    }
}