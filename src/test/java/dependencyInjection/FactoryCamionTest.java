package dependencyInjection;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import vehiculos.Camion;

public class FactoryCamionTest {
	
	Camion camion;
	
	@Before
	public void init() {
		camion = Factory.getObject(Camion.class);
	}

	@Test
	public void elCamionDebeSerInstanciadoAunqueNoEsComponent() {
		assertNotNull(camion);
	}

}
