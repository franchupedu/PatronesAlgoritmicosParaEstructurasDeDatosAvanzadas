package dependencyInjection;

import java.util.List;

@Component
public class Auto {
	 @Injected
	 private Motor motor;

	 @Injected(count=4)
	 private Rueda[] ruedas;

	 @Injected(count=4,implementation=List.class)
	 private List<Asiento> asientos;
	 
	 public String queseyo = "jijr";

	 public void printProperties() {
		 System.out.println(motor);
		 motor.printProperties();
	 }
}
