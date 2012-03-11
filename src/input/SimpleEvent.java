package input;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleEvent extends Event {
	public SimpleEvent(String otitle, String start, String end, String url,
			String descri, Map<String,String> tagSet) {
		super(otitle, start, end, url, descri,tagSet);
	}
	public boolean isThisKindOfEvent(String type)
	{
		return type.equals("SimpleEvent");
	}
}
