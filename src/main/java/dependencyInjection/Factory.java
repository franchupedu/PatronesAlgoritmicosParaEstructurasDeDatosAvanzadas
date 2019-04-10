package dependencyInjection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.lang.Class;

public class Factory {
	
	//T = tipo generico
	public static <T> T getObject(Class<T> objectClass) {
		//printClassMetadata(objectClass);
		//printFieldsMetadata(objectClass);
		//Creo el objecto de la clase objectClass
		System.out.println("Instanciando '" + objectClass.getName() + "'");
		T object = createObject(objectClass);
		System.out.println("-Inyectando dependencias");
		object = inyectDependencies(object);
		System.out.println("'" + objectClass.getName() + "' instanciado con exito!");
		return object;
	}
	
	public static <T> T inyectDependencies(T parentObject){
		//Lista de propiedades
		Field[] campos = parentObject.getClass().getDeclaredFields();

        for (Field campo : campos) {
            //Tiene @Injected?
        	Injected injected = campo.getAnnotation(Injected.class);   
            if ( injected != null ) {
            	Class<?> fieldClass = getFieldClass(campo); //Clase del field   
            	  
            	//La clase tiene @Component?
            	if(isComponent(fieldClass)) {
            		System.out.println("--Inyectando el campo '" + campo.getName() + "'");
            		//TODO Arrays - Interfaces - Singleton
            		//LISTS
            		if ( injected.count() > 1 && fieldIsList(campo) ) {//Si tiene count > 1 y es una coleccion
            			List<Object> listValue = new ArrayList<Object>();
            			for(int i = 0; i < injected.count(); i++) {//Injecto un objeto en el list segun el count
            				listValue.add(getObject(fieldClass));
            			}
            			setField(parentObject, campo, listValue);//Le asigno el valor de la lista al campo del parentObject
					} 
            		//OTROS CASOS
            		else {
	            		Object fieldValue = getObject(fieldClass);
	            		setField(parentObject, campo, fieldValue);//Le asigno al field del parentObject el fieldValue
					}
            			      
            	}
            }
        }
        
		return parentObject;
	}
	
	//Devuelve la clase de un campo. Si es coleccion, devuelve la clase parametrizada
	public static Class<?> getFieldClass(Field campo){
    	Class<?> fieldClass = null; 
    	if(fieldIsList(campo)) {
    		fieldClass = getListFieldParametizedClass(campo);
    	}
    	else {
    		fieldClass = campo.getType();
    	}
    	return fieldClass;
	}
	
	//Devuelve la clase parametrizada en un List 
	public static Class<?> getListFieldParametizedClass(Field campo) {
        Type type = campo.getGenericType();
        if (type instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) type;
            Class<?> tClass = (Class<?>) paramType.getActualTypeArguments()[0];
            return tClass;      
        } else {
            //System.err.println("not parameterized");
        }       
        return null;
	}
	
	//Setea el campo del objecto con el valor enviado
	public static <T> void setField(T object, Field campo, Object value) {
		campo.setAccessible(true);//Para setear campos con private
		//Seteo el campo del parentObject con el objeto devuelto por el factory
		try {
			campo.set(object, value);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		campo.setAccessible(false);		
	}
	
	//Devuelve instancia de la clase objectClass
	public static <T> T createObject(Class<T> objectClass) {
		T object = null;
		Constructor<T> constructor = null;
		//Guardo el constructor
		try {
			constructor = objectClass.getConstructor();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Creo el objecto en base al constructor
		try {
			//El array son los args del constructor
			object = constructor.newInstance(new Object[] {});
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;		
	}
	
	//Si la clase tiene el annotation 'Component' devuelvo true
	//Solo se inyectan las clases que tengan @Component
	public static <T> boolean isComponent(Class<T> classToCheck) {
		return classToCheck.getAnnotation(Component.class) != null;
	}

	public static boolean isInjectable(Field campo) {
		return campo.getAnnotation(Injected.class) != null;
	}
	
	public static <T> void printClassMetadata(Class<T> objectClass) {
		if(isComponent(objectClass)) {
			System.out.println("Es componente");
		}
		else {
			System.out.println("No es componente :_(");
		}
	}

	public static <T> void printFieldsMetadata(Class<T> objectClass) {
		//Guardo las propiedades de la clase en una lista
		Field[] campos = objectClass.getDeclaredFields();
		//Me fijo si las propiedades tienen @Injected
        for (Field campo : campos) {
        	//Guardo la anotacion (null si no existe)
            Injected injectedAnnotation = campo.getAnnotation(Injected.class);
            String fieldName = campo.getName();
            //Tiene @Injected
            if (injectedAnnotation != null) {
            	System.out.println("Campo '" + fieldName + "' es inyectable");
            }
            //No tiene @Injected
            else {
            	System.out.println("Campo '" + fieldName + "' no es inyectable :(");
            }
        }		
	}
	
	public static boolean fieldIsList(Field campo) {
		return Collection.class.isAssignableFrom(campo.getType());
	}
}
