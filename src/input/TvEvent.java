package input;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TvEvent extends Event {
	List<String>actorList; 
	public TvEvent(String otitle, String start, String end, String url,
			String descri, List<String> actors, Map<String,String> tagSet) {
		super(otitle, start, end, url, descri,tagSet);
		actorList = actors;
	}
	public boolean isThisKindOfEvent(String type)
	{
		return type.equals("TVEvent");
	}
}
