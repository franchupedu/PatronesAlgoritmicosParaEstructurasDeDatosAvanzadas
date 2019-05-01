package vehiculos;

import dependencyInjection.Factory;

public class MainEjectuable {
	
	public static <T> T objeto(T objetoss) {
		T objetos = (T) objetoss;
		return objetos;
	}
	
	public static void main(String[] args) {
        /*List<Object> lista = new ArrayList<Object>();
        lista.add(new Libro("titulo1", "pedro"));
        procesar(lista);*/
		
		//Entero entero = new Entero();
		//Entero objetos = objeto(entero);
		//objetos.setEntero(5);
		//objetos.getEntero();
		
		Auto auto = Factory.getObject(Auto.class);
		auto.printProperties();
	}
}