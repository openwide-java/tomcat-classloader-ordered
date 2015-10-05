package fr.openwide.tomcat.catalina.loader;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.loader.WebappClassLoaderBase;

/**
 * This classloader is designed to return the jar of WEB-INF lib in alphabetical order as it was the case with Tomcat
 * 7.x.
 * 
 * See the discussion in https://bz.apache.org/bugzilla/show_bug.cgi?id=57129 for more information.
 */
public class WebappOrderedClassLoader extends WebappClassLoaderBase {

	public WebappOrderedClassLoader() {
	}

	public WebappOrderedClassLoader(ClassLoader parent) {
		super(parent);
	}
	
	@Override
	public void setResources(WebResourceRoot resources) {
		super.setResources(new OrderedWebResourceRoot(resources));
	}
	
	@Override
	public WebappOrderedClassLoader copyWithoutTransformers() {
		WebappOrderedClassLoader result = new WebappOrderedClassLoader(getParent());

		super.copyStateWithoutTransformers(result);

		try {
			result.start();
		} catch (LifecycleException e) {
			throw new IllegalStateException(e);
		}

		return result;
	}

	@Override
	protected Object getClassLoadingLock(String className) {
		return this;
	}
}