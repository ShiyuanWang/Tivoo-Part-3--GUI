package processor;

import input.Event;

import java.util.Comparator;

public class TitleComparator extends ParentComparator {
	@Override
	// Title is primary concern, then StartTime, then End time
	public int compare(Event o1, Event o2) {
		getCompareInfo(o1, o2);
		return getCompareResult(compareInfo.get(0), compareInfo.get(1),
				compareInfo.get(2));
	}
}