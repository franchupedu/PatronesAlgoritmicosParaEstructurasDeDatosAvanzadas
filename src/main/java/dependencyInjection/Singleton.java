package dependencyInjection;

import java.util.HashSet;

public final class Singleton {
	
	public static final HashSet<Object> singletonInstances = new HashSet<Object>();
	
	public static final HashSet<Object> getSingletonInstances() {
		return singletonInstances;
	}
	
}