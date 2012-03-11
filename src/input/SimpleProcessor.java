package input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.Element;



//just a simple test case
public class SimpleProcessor extends CalParser{

    public ArrayList<Event> parseEvent(Element root) {
        List<Element> events = root.getChildren("staff");
        ArrayList<Event> filterEvents = new ArrayList<Event>();

        for (int i = 0; i < events.size(); i++) {
            Map<String,String> tagSet = super.getTags(events.get(i),new HashMap<String,String>());
            filterEvents.add(new SimpleEvent("title", "start", "end", "url", "des", tagSet));
        }
        return filterEvents;
    }

    @Override
    public boolean isThisKindof() {
        return ("resources/simpletest.xml").contains(fileName);
    }
//    public static void main(String[]args){
//        CalendarUtil util=new CalendarUtil();
//        String[] files={"resources/simpletest.xml"};
//        List<Event>events=util.parseFiles(files);
//        System.out.println("events:"+events.size());
//        Set<String>so=events.get(0).getTags();
//        System.out.println(so.toString());
//    }
}



