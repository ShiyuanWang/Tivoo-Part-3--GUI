package processor;

import input.Event;
import java.util.Comparator;


public class EndTimeComparator extends ParentComparator {

	@Override
	// EndTime is primary concern, then title, then start time
	public int compare(Event o1, Event o2) {
		getCompareInfo(o1, o2);
		return getCompareResult(compareInfo.get(2), compareInfo.get(0),
				compareInfo.get(1));
	}
} 