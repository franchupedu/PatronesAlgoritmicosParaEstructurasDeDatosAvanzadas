package vehiculos;

import dependencyInjection.Factory;

public class MainEjectuable {
	
   public static void main(String[] args) {
	   
        /*List<Object> lista = new ArrayList<Object>();
        lista.add(new Libro("titulo1", "pedro"));
        procesar(lista);*/
	   
	   Auto a = Factory.getObject(Auto.class);
	   //a.printProperties();	   
    }
}