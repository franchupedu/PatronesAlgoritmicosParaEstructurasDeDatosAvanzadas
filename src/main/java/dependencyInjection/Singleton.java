package dependencyInjection;

import java.util.Iterator;
import java.util.LinkedList;

public final class Singleton {
	
	public static final LinkedList<Object> singletonInstances = new LinkedList<Object>();
	
	public static final LinkedList<Object> getSingletonInstances() {
		return singletonInstances;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getObjectOfClass(Class<T> objectClass) {
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