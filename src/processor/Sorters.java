package processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import input.Event;

public abstract class Sorters {


    public List<Event> sort(List<Event>events){
        List<Event> events2 = new ArrayList<Event>();
        events2.addAll(events);
        Collections.sort(events2, getComparator());
        return events2;
    }
    
    protected abstract Comparator<Event> getComparator();

    public List<Event> reverseSort(List<Event>events){
        List<Event>events2;
        events2=sort(events);
        Collections.reverse(events2);
        return events2;
    }
}