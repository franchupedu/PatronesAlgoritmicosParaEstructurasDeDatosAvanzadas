package vehiculos;

import java.util.List;

import dependencyInjection.Component;
import dependencyInjection.Injected;

@Component
public class Auto implements Vehiculo{
	 //@Injected(implementation=Moto.class)
	 //private Vehiculo pruebaDeInterfaz;
	
	 @Injected(singleton=true)
	 private Motor motor;
	 
	 @Injected(count=4)
	 private Rueda[] ruedas;

	 @Injected(count=5,implementation=List.class)
	 private List<Asiento> asientos;
	 
	 private String queseyo = "jijr";
	 
	 private Boolean tieneQueSerNulo;
	 
	 public void printProperties() {
		 System.out.println(this.getMotor());
		 this.getMotor().printProperties();
		 System.out.println(this.getRuedas());
		 System.out.println(this.getAsientos());
		 System.out.println(this.getQueseyo());
		 System.out.println(this.getTieneQueSerNulo());
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
}
