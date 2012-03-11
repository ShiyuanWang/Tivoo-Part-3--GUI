package processor;

import input.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class FilterComponent {
    

	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmm");


	public List<Event> filter(List<Event> events, String[] keyWords) 
	{
		List<Event> filtEvents = new ArrayList<Event>();
		for (Event i : events) 
		{
			for(String keyWord : keyWords)
			{ 
				if (filterSpecificEvent(i,keyWord))
				{
					filtEvents.add(i);
					i.stringOutput();
				}
			}
		}
		return filtEvents;
	}

	abstract boolean filterSpecificEvent(Event i, String keyWord);
	
	protected Date parseStringYMdhmToDate(String date) 
	{
        Date output = null;
        try 
        {
            output = simpleDateFormat.parse(date);
        } catch (ParseException e) 
        {
            System.err.println("Cannot parse date "+date+" into year-month-date-hour-minute format");
            e.printStackTrace();
            return null;
        }
        return output;
	}
}