package loco;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import dependencyInjection.Factory;
import vehiculos.Auto;

public class PruebaAnnotations {
	
   public static void main(String[] args) { 
        List<Object> lista = new ArrayList<Object>();
        lista.add(new Libro("titulo1", "pedro"));
        procesar(lista);
    }
 
    public static void procesar(List<Object> lista) {
 
        try {
            for (Object o : lista) {
                Field[] campos = o.getClass().getDeclaredFields();
                 
                for (Field campo : campos) {
 
                    Imprimible imprimir = campo.getAnnotation(Imprimible.class);
                    System.out.println(imprimir);
                    if (imprimir != null) {
                        if (imprimir.mayusculas()) {
                            System.out.println(campo.get(o).toString().toUpperCase());
                        } else {
                            System.out.println(campo.get(o).toString());
 
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
