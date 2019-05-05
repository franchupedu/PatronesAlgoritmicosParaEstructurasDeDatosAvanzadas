package dependencyInjection;

import org.junit.Before;
import org.junit.Test;

import vehiculos.Auto;
import dependencyInjection.Factory;

public class FactoryAutoTest {
	
	Auto auto;
	
	@Before
	public void init() {
		auto = Factory.getObject(Auto.class);
	}
	
	@Test
	public void inyectorNoDevuelveNull() {
		assert( auto != null);
	}
	
	@Test
	public void autoTieneMotor() {
		assert( auto.getMotor() != null);
	}
	
	@Test
	public void autoTieneRuedas() {
		assert( auto.getRuedas() != null);
	}
	
	@Test
	public void autoNoTieneAlgoQueNoInstancio() {
		assert( auto.getTieneQueSerNulo() == null);
	}
	
	@Test
	public void autoTieneAsientos() {
		assert( auto.getAsientos() != null);
	}
	
	@Test
	public void autoTienePropiedadInstanciadaPorClase() {
		assert( auto.getQueseyo() != null);
	}

	@Test
	public void motorEsSingleton() {
		Auto auto2 = Factory.getObject(Auto.class);
		assert( auto.getMotor() == auto2.getMotor() );
	}
	
	@Test
	public void autoNoEsSingleton() {
		Auto auto2 = Factory.getObject(Auto.class);
		assert( auto != auto2 );
	}

	@Test
	public void autoTieneArrayDeCuatroRuedas() {
		assert( auto.getRuedas().length == 4);
	}
	
	@Test
	public void autoTieneListaDe5Asientos() {
		assert( auto.getAsientos().size() == 5);
	}
}
