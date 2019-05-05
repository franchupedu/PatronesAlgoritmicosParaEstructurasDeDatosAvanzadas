package dependencyInjection;

import java.util.Iterator;
import java.util.HashSet;

public final class Singleton {
	
	public static final HashSet<Object> singletonInstances = new HashSet<Object>();
	
	public static final HashSet<Object> getSingletonInstances() {
		return singletonInstances;
	}
	
	@SuppressWarnings("unchecked")
	public static final <T> T getObjectOfClass(Class<T> objectClass) {
		Iterator<Object> iterator = getSingletonInstances().iterator();
		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (objectClass.isInstance(next)) {
				return (T) next;
			}
		}
		return null;
	}
	
}