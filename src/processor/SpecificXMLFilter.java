package processor;

import java.util.Map;

import input.Event;

import org.jdom.JDOMException;

/**
 * Only filters events coming from specific xml file, will return others with no
 * filtering.
 * 
 * @author Wendy
 * 
 */
public class SpecificXMLFilter extends FilterComponent {
    @Override
    boolean filterSpecificEvent(Event i, String keyWord) {
        String[] keys = keyWord.split("~");
        if (keys.length != 3) {
            throw new Error(
                    "Did not pass in proper details for filterSpecificEvent. Must be of format XML-TAG-TERM");
        }
        if (!i.isThisKindOfEvent(keys[0])){return true;} //don't do any filtering
        Map<String, String> subTags = i.getSubTags();

        return (checkTag(subTags, keys[1]))
                && (checkKeyword(subTags, keys[1], keys[2]));

    }
    private boolean checkTag(Map<String, String> subTags, String key) {
        return (subTags.containsKey(key));
    }

    private boolean checkKeyword(Map<String, String> subTags, String key,
            String val) {
        return (subTags.containsKey(key)&&(subTags.get(key).contains(val)));
    }
}