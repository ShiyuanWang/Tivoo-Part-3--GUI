package processor;

import input.Event;

import java.util.Comparator;

public class SortByTitle extends Sorters {
    
    @Override
    protected Comparator<Event> getComparator() {
        return new TitleComparator();
    }
}