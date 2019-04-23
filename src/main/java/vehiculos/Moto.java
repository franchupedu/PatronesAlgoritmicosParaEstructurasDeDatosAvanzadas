package vehiculos;

import dependencyInjection.Component;
import dependencyInjection.Injected;

@Component
public class Moto implements Vehiculo{
	 @Injected(count=2)
	 private Rueda[] ruedas;
}
