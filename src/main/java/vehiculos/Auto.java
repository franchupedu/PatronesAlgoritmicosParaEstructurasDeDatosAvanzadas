package vehiculos;

import java.util.List;

import dependencyInjection.Component;
import dependencyInjection.Injected;

@Component
public class Auto implements Vehiculo{
	 @Injected(implementation=Moto.class)
	 private Vehiculo pruebaDeInterfaz;
	
	 @Injected(singleton=true)
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
