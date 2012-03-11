package input;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class NFLParser extends CalParser{
	
	public boolean isThisKindof() {

	return ("resources/NFL.xml").contains(fileName);
	}

		public ArrayList<Event> parseEvent(Element root){
			List<Element> events = root.getChildren("row"); 
			ArrayList<Event> filterEvents  = new ArrayList<Event>();
			for(int i = 0; i <events.size(); i++){
				Map<String,String>tagSet = super.getTags(events.get(i),new HashMap<String,String>());
				String title = events.get(i).getChildText("Col1");			
				String link = events.get(i).getChildText("Col2");
				String starttime = genTimeStamp(events.get(i).getChildText("Col8"));
				String endtime = genTimeStamp(events.get(i).getChildText("Col9"));
                Event event = new NFLEvent(title,starttime,endtime,link,"",tagSet);
                filterEvents.add(event);
			}
			return filterEvents;
		}
	
      private String genTimeStamp(String time)
      {
    	  String[] temp = time.split(" ");
    	  String date = temp[0].replace("-","");
    	  temp = temp[1].split(":");
    	  temp[0] = Integer.toString(Integer.parseInt(temp[0])+12);
    	  return date+temp[0]+temp[1];
      }

}
