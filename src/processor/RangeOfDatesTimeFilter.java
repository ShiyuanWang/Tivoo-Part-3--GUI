package processor;

import java.util.Date;

import input.Event;

public class RangeOfDatesTimeFilter extends FilterComponent {

	@Override
	boolean filterSpecificEvent(Event i, String keyWord) {

		String[] splitStringArray = keyWord.split("~");
		if (splitStringArray.length == 2) {
			Date eventDate = parseStringYMdhmToDate(i.getStartTime());
			Date start = parseStringYMdhmToDate(splitStringArray[0]);
			Date end = parseStringYMdhmToDate(splitStringArray[1]);

			if(eventDate.getTime() >= start.getTime() && eventDate.getTime() <=end.getTime()){
				return true; 
			}
		}
		else{
			System.err.println("RangeOfDatesTimeFilterError: Please enter the right date range format");
		}
		return false;
	}
}