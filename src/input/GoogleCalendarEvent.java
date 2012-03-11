package input;

import java.util.Map;
import java.util.Set;

public class GoogleCalendarEvent extends Event 
{
	public GoogleCalendarEvent(String otitle, String start, String end, String url,
			String descri,Map<String,String> tagSet) {
		super(otitle, start, end, url, descri,tagSet);
	}
	public boolean isThisKindOfEvent(String type)
	{
		
		
		return type.equals("GoogleCalendarEvent");
	}

}
