package vehiculos;

import java.util.List;

import dependencyInjection.Component;

@Component
public class Rueda {
	 
	 private List<Tuerca> tuercas;

	public List<Tuerca> getTuercas() {
		return tuercas;
	}

	public void setTuercas(List<Tuerca> tuercas) {
		this.tuercas = tuercas;
	}

}
