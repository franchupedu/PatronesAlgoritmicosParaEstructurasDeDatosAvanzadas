package vehiculos;

import java.util.List;

import dependencyInjection.Component;
import dependencyInjection.Injected;

@Component
public class Motor {
	 @Injected(count=3)
	 private List<Tuerca> tuercas;
	 
	 public void printProperties() {
		 System.out.println(tuercas);
	 }	 
}
