package processor;


import input.Event;
import java.util.Comparator;

public class StartTimeComparator extends ParentComparator {

@Override
public int compare(Event o1, Event o2) {
getCompareInfo(o1, o2);
return getCompareResult(compareInfo.get(1), compareInfo.get(0),
compareInfo.get(2));
}

}