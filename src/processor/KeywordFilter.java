package processor;

import input.Event;

import java.util.ArrayList;
import java.util.List;

import org.jdom.JDOMException;

public class KeywordFilter extends FilterComponent
{
    @Override
    boolean filterSpecificEvent(Event i, String keyWord) 
    {
        return(i.getTitle().contains(keyWord));
    }
}