package input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.Element;

public class TvParser extends CalParser{

	@Override
	public boolean isThisKindof() {
		 return ("resources/tv.xml").contains(fileName);
	}

	@Override
	public ArrayList<Event> parseEvent(Element root) {
	 
		    List<Element> events = root.getChildren("programme");
	  
	        ArrayList<Event> filterEvents = new ArrayList<Event>();

	        for (int i = 0; i < events.size(); i++) {
	        	Map<String,String> tagSet = super.getTags(events.get(i),new HashMap<String,String>());
	            String title = events.get(i).getChildText("title");
	            String starttime = events.get(i).getAttributeValue("start").substring(0,12);
	            String endtime = events.get(i).getAttributeValue("end").substring(0,12);
	            String link = null;
	            StringBuilder str = new StringBuilder();
	            str.append(events.get(i).getChildText("descr")+" ActorList: ");
	            ArrayList<Element>actorlist = (ArrayList<Element>) events.get(i).getChildren("actor");
	            List<String>actors = new ArrayList<String>();
	            for(int j = 0; j< actorlist.size(); j++)
	            {
	            	actors.add(actorlist.get(i).getText());
	            }
	            Event event = new TvEvent(title,starttime,endtime,link,str.toString(),actors,tagSet);
	            filterEvents.add(event);

	        }
		return null;
	}

}
