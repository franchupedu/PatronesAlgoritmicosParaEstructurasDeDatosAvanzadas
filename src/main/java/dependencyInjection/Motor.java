package dependencyInjection;

import java.util.List;

@Component
public class Motor {
	 @Injected(count=25)
	 private List<Tuerca> tuercas;
	 
	 public void printProperties() {
		 System.out.println(tuercas);
	 }	 
}
