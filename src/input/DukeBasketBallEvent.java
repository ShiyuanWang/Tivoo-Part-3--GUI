package input;

import java.util.Map;

public class DukeBasketBallEvent extends Event  {
	
	public DukeBasketBallEvent(String otitle, String start, String end, String url,
			String descri, Map<String,String>set) {
		super(otitle, start, end, url, descri,set);
	}
	
	public boolean isThisKindOfEvent(String type)
	{
		
		
		return type.equals("DukeBasketBallEvent");
	}
}
