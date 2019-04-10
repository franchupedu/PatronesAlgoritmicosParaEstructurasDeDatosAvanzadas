package dependencyInjection;

@Component
public class Auto {
	 @Injected
	 private Motor motor;

	 @Injected(count=4)
	 private Rueda[] ruedas;
	 
	 public String queseyo = "jijr";

	 public void printProperties() {
		 System.out.println(motor);
		 motor.printProperties();
	 }
}
