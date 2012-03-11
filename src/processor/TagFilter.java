package processor;

import input.*; 
import java.util.*;

import org.jdom.JDOMException;

public class TagFilter extends FilterComponent
{
	@Override
	boolean filterSpecificEvent(Event i, String keyWord)
	{
		if (i.getTags().equals(keyWord))
		{
			return true;
		}
		return false;
	}
}