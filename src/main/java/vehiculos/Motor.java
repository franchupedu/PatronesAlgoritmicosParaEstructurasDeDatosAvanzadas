package vehiculos;

import java.util.List;

import dependencyInjection.Component;
import dependencyInjection.Injected;

@Component(singleton = true)
public class Motor {
	 @Injected(count=3)
	 private List<Tuerca> tuercas;
	 
	 public void printProperties() {
		 System.out.println(getTuercas());
	 }

	public List<Tuerca> getTuercas() {
		return tuercas;
	}

	public void setTuercas(List<Tuerca> tuercas) {
		this.tuercas = tuercas;
	}	 
	 
	 
}
