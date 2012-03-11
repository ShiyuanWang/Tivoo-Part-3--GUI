package processor;

import input.Event;

import java.util.ArrayList;
import java.util.Comparator;


public abstract class ParentComparator implements Comparator<Event>{
	/**
	 * sets compareInfo to values corresponding to the string comparisons for 
	 * [title,start time, end time]
	 */

	protected ArrayList<Integer> compareInfo; 

	public void getCompareInfo(Event o1, Event o2) { 
		compareInfo = new ArrayList<Integer>();
		compareInfo.add(((Event) o1).getTitle().compareTo(
				(((Event) o2).getTitle())));
		compareInfo.add(((Event) o1).getStartTime().compareTo(
				(((Event) o2).getStartTime())));
		compareInfo.add(((Event) o1).getEndTime().compareTo(
				(((Event) o2).getEndTime())));
	}

    public int getCompareResult(int firstConcern, int secondConcern,
            int thirdConcern) {
        if (firstConcern==0){
            if (secondConcern == 0) {
                return thirdConcern;
            } else {
                return secondConcern;
            }
        }
        return firstConcern;
    }
}