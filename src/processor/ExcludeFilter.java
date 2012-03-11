package processor;

import input.Event;

import java.util.ArrayList;
import java.util.List;

import org.jdom.JDOMException;

public class ExcludeFilter extends FilterComponent 
{
	@Override
	boolean filterSpecificEvent(Event i, String keyWord) 
	{
		if(i.getTitle().contains(keyWord))
		{
			return false;
		}
		return true;
	}
}


