package input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public abstract class CalParser {

    protected String fileName;
    private List<Event> events;

    public CalParser loadFile(String name) {
        fileName = name;
        return this;
    }

    public abstract boolean isThisKindof();

    public abstract ArrayList<Event> parseEvent(Element root);

    public List<Event> parser() {
        File inputXml = new File(fileName);
        SAXBuilder saxBuilder = new SAXBuilder();
        events = new ArrayList<Event>();
        try {
            Document document = saxBuilder.build(inputXml);
            events.addAll(parseEvent(document.getRootElement()));
        } catch (JDOMException e) {
            System.err.println("Problem building parsed document");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Problem opening file " + fileName);
            e.printStackTrace();
        }
        return events;
    }

    protected Map<String, String> getTags(Element i, Map<String, String> subTags) {
        String values = "";
        if (subTags.containsKey(i.getName())) {
            values = subTags.get(i.getName());
        }
        subTags.put(i.getName(), values + " " + i.getText().trim());
        
        if (i.getChildren().size() != 0) {
            List<Element> children = i.getChildren();
            for (Element child : children) {
                subTags = getTags(child, subTags);
            }
        }
        return subTags;
    }

}