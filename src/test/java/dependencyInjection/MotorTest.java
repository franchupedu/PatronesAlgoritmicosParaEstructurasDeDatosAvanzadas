package dependencyInjection;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vehiculos.Motor;
import dependencyInjection.Factory;

public class MotorTest {
	
	Motor motor;
	
	@Before
	public void setUp() throws Exception {
		motor = Factory.getObject(Motor.class);
	}
	
	@Test
	public void inyectorNoDevuelveNullConMotor() {
		assertNotNull(motor);
	}
	
	@Test
	public void motorTieneTuercas() {
		assertNotNull(motor.getTuercas());
	}
	
	@Test
	public void motorTiene3Tuercas() {
		assertEquals(3, motor.getTuercas().size());
	}

}
