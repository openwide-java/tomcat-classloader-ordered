package fr.openwide.tomcat.catalina.loader;

import java.util.Arrays;

import org.apache.catalina.Context;
import org.apache.catalina.WebResource;
import org.apache.catalina.webresources.StandardRoot;

/**
 * This StandardRoot implementation is designed to return the jar of WEB-INF lib in alphabetical order as it was the case with Tomcat
 * 7.x.
 * 
 * See the discussion in https://bz.apache.org/bugzilla/show_bug.cgi?id=57129 for more information.
 */
public class OrderedStandardRoot extends StandardRoot {
	public OrderedStandardRoot() {
		super();
	}

	public OrderedStandardRoot(Context context) {
		super(context);
	}
	
	@Override
	protected WebResource[] listResources(String path, boolean validate) {
		WebResource[] result = super.listResources(path, validate);
		if (OrderedWebResourceRoot.WEB_INF_LIB_PATH.equals(path)) {
			Arrays.sort(result, OrderedWebResourceRoot.WEB_RESOURCE_COMPARATOR);
		}
		return result;
	}
}
