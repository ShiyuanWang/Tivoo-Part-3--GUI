package input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.Element;

public class GoogleCalendarParser extends CalParser {
//    private List<Event> eventList;
	@Override
	public boolean isThisKindof() {
//		System.out.println(fileName);
		return ("resources/googlecal.xml").contains(fileName);
	}

	public ArrayList<Event> parseEvent(Element root) {

		List<Element> events = root.getChildren();

		List<Element> entry = forEntry(events);

		ArrayList<Event> filterEvents = new ArrayList<Event>();

		for (int i = 0; i < entry.size(); i++) {

			Map<String, Element> componentMap = forComponent(entry.get(i));
            Map<String,String> tagSet = super.getTags(entry.get(i),new HashMap<String,String>());
			String[] date = componentMap.get("content").getText().split(" ");
			String startTime = null;
			String endTime = null;
			StringBuilder str = new StringBuilder();
          
			if (date[0].equals("When:")) {
				date[3] = date[3].substring(0, date[3].length() - 1);
				str.append(date[4].substring(0, 4)).append(parseMonth(date[2]))
						.append(parseDay(date[3]));
			    startTime = str.toString()+genDetailTime(date[5]);
			    endTime = str.toString()+genDetailTime(date[7]);
			}
        
			if (date[0].equals("Recurring")) {
				String firstDateStamp = date[4].replaceAll("-", "");
				str = str.append(firstDateStamp);
				startTime = str.toString()+date[5].replaceAll(":","").substring(0,4);
				endTime = startTime;
				//int duration = Integer.parseInt(date[9]);
				//endTime = startTime.substring(0,8)+Integer.toString((Integer.parseInt(startTime.substring(8,10))+duration/60))
				//		+Integer.toString((Integer.parseInt(startTime.substring(10))+duration%60));
				
			}

			Event event = new GoogleCalendarEvent(componentMap.get("title").getText(),
					startTime, endTime, componentMap.get("id")
							.getText(),"",tagSet);
			filterEvents.add(event);

		}
//		eventList = filterEvents;
		return filterEvents;
	}

    private Map forComponent(Element entry) {
		Map<String, Element> componentMap = new HashMap<String, Element>();
		List<Element> entryChildren = entry.getChildren();
		for (Element e : entryChildren) {
			if (e.getName().equals("content")) {
				componentMap.put("content", e);
			}
			if (e.getName().equals("id"))
				componentMap.put("id", e);
			if (e.getName().equals("title"))
				componentMap.put("title", e);

		}
		return componentMap;
	}

	private List<Element> forEntry(List<Element> events) {
		List<Element> entry = new ArrayList<Element>();
		for (Element e : events) {
			if (e.getName().equals("entry"))
				entry.add(e);
		}
		return entry;
	}

	private String parseMonth(String month) {
		String mon = null;
		String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
				"Aug", "Sep", "Oct", "Nov", "Dec" };
		for (int k = 0; k < 12; k++) {
			if (month.equals(MONTHS[k])) {
				mon = String.valueOf(k + 1);
				if (k > 0 && k < 9)
					mon = "0" + mon;
			}
		}
		return mon;
	}

	private String parseDay(String day) {
		String we = day;
		int k = Integer.parseInt(day);
		if (k > 0 && k < 10)
			we = "0" + we;
		return we;

	}
	
	private String genDetailTime(String time)
	{   String[] temp = null;
		if(!time.contains("pm")&&!time.contains("am"))
			return "0000";
	    
	   if(time.contains("pm")&&time.contains(":"))
		{
			temp = time.split(":");
			temp[0] = Integer.toString(Integer.parseInt(temp[0])+12);
			return temp[0]+temp[1].substring(0,2);
		}
		if(time.contains("pm")&&!time.contains(":"))
		{
			if(time.length()==3)
				return "0"+Integer.toString(Integer.parseInt(time.substring(0,1))+12)+"00";
			else
			return Integer.toString(Integer.parseInt(time.substring(0,2))+12)+"00";
		}
	    if(!time.contains("pm")&&time.contains(":"))
	    {
	    	temp = time.split(":");
	    	if(temp[0].length() == 1)
	    		temp[0] = "0" + temp[0]; 
	         return temp[0] + temp[1].substring(0,2);
	    }
		if(!time.contains("pm")&&!time.contains(":"))
		{
			if(time.length()==3)
			{
				return "0"+time.substring(0,1)+"00";
			}
			else
				return time.substring(0,2)+"00";
		}
	
		return null;
	}
	//
	// public int getweekday(String timeStamp)
	// {
	// Calendar weeks=Calendar.getInstance();
	// String year=timeStamp.substring(0, 4);
	// String month=timeStamp.substring(4, 6);
	// String day=timeStamp.substring(6, 8);
	// weeks.set(Integer.parseInt(year), Integer.parseInt(month)-1
	// ,Integer.parseInt(day));
	// return weeks.get(Calendar.DAY_OF_WEEK);
	// }

}