package dependencyInjection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import vehiculos.Auto;
import vehiculos.Moto;
import vehiculos.Rueda;
import vehiculos.Vehiculo;
import dependencyInjection.Factory;

public class FactoryAutoTest {
	
	Auto auto;
	
	@Before
	public void init() {
		auto = Factory.getObject(Auto.class);
	}
	
	@Test
	public void inyectorNoDevuelveNullConAuto() {
		assertNotNull(auto);
	}
	
	@Test
	public void autoTieneMotor() {
		assertNotNull(auto.getMotor());
	}
	
	@Test
	public void autoTieneRuedas() {
		assertNotNull(auto.getRuedas());
	}
	
	@Test
	public void autoNoTieneAlgoQueNoInstancio() {
		assertNull(auto.getTieneQueSerNulo());
	}
	
	@Test
	public void autoTieneAsientos() {
		assertNotNull(auto.getAsientos());
	}
	
	@Test
	public void autoTienePropiedadInstanciadaPorClase() {
		assertNotNull(auto.getQueseyo());
	}

	@Test
	public void motorEsSingleton() {
		Auto auto2 = Factory.getObject(Auto.class);
		assertTrue( auto.getMotor() == auto2.getMotor() );
	}
	
	@Test
	public void autoNoEsSingleton() {
		Auto auto2 = Factory.getObject(Auto.class);
		assertTrue( auto != auto2 );
	}

	@Test
	public void autoTieneArrayDeCuatroRuedas() {
		assertEquals(4, auto.getRuedas().length);
	}
	
	@Test
	public void autoTieneListaDe5Asientos() {
		assertEquals(5, auto.getAsientos().size());
	}
	
	@Test
	public void elMotorDelAutoTiene25Tuercas() {
		assertEquals(3, auto.getMotor().getTuercas().size());
	}
	
	@Test
	public void queseyoSeInstanciaConjijr(){
		assertEquals("jijr", auto.getQueseyo());
	}
	
	@Test
	public void laRuedaNoInstanciaTuercas(){
		Rueda[] ruedas = auto.getRuedas();
		for (int i = 0; i < ruedas.length; i++) {
			assertNull(ruedas[i].getTuercas());
		}
	}
	
	@Test
	public void pruebaDeInterfazNoEstaVacia(){
		assertNotNull(auto.getPruebaDeInterfaz());
	}
	
	@Test
	public void pruebaDeInterfazEsUnaInstanciaDeMoto(){
		assertTrue(auto.getPruebaDeInterfaz() instanceof Moto );
	}
	
	@Test
	public void pruebaDeInterfazImplementaVehiculo(){
		assertEquals(Vehiculo.class, auto.getPruebaDeInterfaz().getClass().getGenericInterfaces()[0]);
	}
}
