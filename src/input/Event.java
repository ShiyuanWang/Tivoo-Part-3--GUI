package input;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Event {
	private String title;
	private String starttime;
	private String endtime;
	private String link;
	private String detail;
	private Map<String,String>tags;

	public Event(String otitle, String start, String end, String url,
			String descri,Map<String,String> set) {
		title = otitle;
		starttime = start;
		endtime = end;
		link = url;
		detail = descri;
		tags = set;
	}
	public boolean isThisKindOfEvent(String type){
	    return type.equals("*");
	}
	
	public void stringOutput() {
		System.out.println("Title : " + title);

		System.out.println("Start Time : " + starttime);

		System.out.println("EndTime : " + endtime);

		System.out.println("Description : " + detail);

		System.out.println("Link : " + link);

		System.out
				.println("---------------------------------------------------------------------------------");
	}

	public String getNameForFile(){
	    String[]words=title.split("\\W");
	    return starttime+"_"+words[0];
	}
	public String getTitle() {
		return title;
	}

	public String getStartTime() {
		return starttime;
	}

	public String getEndTime() {
		return endtime;
	}
    
    public String getFormattedStartTime() {
        String year = starttime.substring(0, 4);
        String month = starttime.substring(4, 6);
        String day = starttime.substring(6, 8);
        String hour = starttime.substring(8, 10);
        String min = starttime.substring(10, 12);
        return year+"/"+month+"/"+day+" "+hour+":"+min;
    }

    public String getFormattedEndTime() {
        String year = endtime.substring(0, 4);
        String month = endtime.substring(4, 6);
        String day = endtime.substring(6, 8);
        String hour = endtime.substring(8, 10);
        String min = endtime.substring(10, 12);
        return year+"/"+month+"/"+day+" "+hour+":"+min;
    }
    
	public String getLink() {
		return link;
	}

	public String getDetail() {
		return detail;
	}
    
	public Set<String> getTags()
    {
	    HashSet<String>copy=new HashSet<String>();
	    copy.addAll(tags.keySet()); //on the off chance someone tries to change the set
    	return copy;
    }
	public Map<String,String> getSubTags(){
	    return Collections.unmodifiableMap(tags);
	}
	
    public int getDayOfWeek() {
		Calendar weeks = Calendar.getInstance();
		String time1 = getStartTime();
		String year = time1.substring(0, 4);
		String month = time1.substring(4, 6);
		String day = time1.substring(6, 8);
		weeks.set(Integer.parseInt(year), Integer.parseInt(month) - 1,
				Integer.parseInt(day));

		int weekday = weeks.get(Calendar.DAY_OF_WEEK);
		return weekday;
	}
    
	/**
	 * @return -1 if time2 less than time1, 0 if =, 1 if time2 greater than time1
	 */
	public int toCompare(String time1, String time2) {
		Calendar week1 = Calendar.getInstance();
		Calendar week2 = Calendar.getInstance();
		String year = time1.substring(0, 4);
		String month = time1.substring(4, 6);
		String day = time1.substring(6, 8);
        String hour = time1.substring(8, 10);
        String min = time1.substring(10,12);
		week1.set(Integer.parseInt(year), Integer.parseInt(month) - 1,
				Integer.parseInt(day),Integer.parseInt(hour),Integer.parseInt(min));
		String year2 = time2.substring(0, 4);
		String month2 = time2.substring(4, 6);
		String day2 = time2.substring(6, 8);
        String hour2 = time2.substring(8, 10);
        String min2 = time2.substring(10,12);
		week2.set(Integer.parseInt(year2), Integer.parseInt(month2) - 1,
				Integer.parseInt(day2),Integer.parseInt(hour2),Integer.parseInt(min2));
		return week2.compareTo(week1);
	}
}
