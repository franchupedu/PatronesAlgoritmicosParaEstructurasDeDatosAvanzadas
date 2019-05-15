package vehiculos;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import dependencyInjection.Component;
import dependencyInjection.Injected;

@Component
public class Auto implements Vehiculo{
	 @Injected(implementation=Moto.class)
	 private Vehiculo pruebaDeInterfaz;
	 
	 @Injected
	 private Motor motor;
	 
	 @Injected(count=4)
	 private Rueda[] ruedas;

	 @Injected(count=2,implementation=LinkedHashSet.class)
	 private Set<Rueda> otrasRuedas;
	 
	 @Injected(count=5,implementation=LinkedList.class)
	 private List<Asiento> asientos;
	 
	 private String queseyo = "jijr";
	 
	 private Boolean tieneQueSerNulo;
	 
	 public void printProperties() {
		 System.out.println(getMotor());
		 getMotor().printProperties();
		 System.out.println(getRuedas());
		 System.out.println(getTieneQueSerNulo());
		 System.out.println(getQueseyo());
		 System.out.println(getAsientos());
	 }

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	public Rueda[] getRuedas() {
		return ruedas;
	}

	public void setRuedas(Rueda[] ruedas) {
		this.ruedas = ruedas;
	}

	public Boolean getTieneQueSerNulo() {
		return tieneQueSerNulo;
	}

	public void setTieneQueSerNulo(Boolean tieneQueSerNulo) {
		this.tieneQueSerNulo = tieneQueSerNulo;
	}

	public String getQueseyo() {
		return queseyo;
	}

	public void setQueseyo(String queseyo) {
		this.queseyo = queseyo;
	}

	public List<Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Asiento> asientos) {
		this.asientos = asientos;
	}

	public Vehiculo getPruebaDeInterfaz() {
		return pruebaDeInterfaz;
	}

	public void setPruebaDeInterfaz(Vehiculo pruebaDeInterfaz) {
		this.pruebaDeInterfaz = pruebaDeInterfaz;
	}
}
