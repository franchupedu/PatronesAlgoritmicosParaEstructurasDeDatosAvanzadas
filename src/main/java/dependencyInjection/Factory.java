package dependencyInjection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.lang.Class;

import org.reflections.Reflections;


public class Factory {
	
	public static <T> T getObject(Class<T> objectClass) {
		System.out.println("Instanciando un objeto de Type '" + objectClass.getSimpleName() + "'");
		T object = createObject(objectClass);//Guardo una instancia de objectClass
		System.out.println("-Inyectando dependencias");
		object = inyectDependencies(object);//Inyectamos dependencias a la instancia
		System.out.println("---Objeto '" + objectClass.getSimpleName() + "' instanciado con exito!");
		return object;
	}
	
	private static <T> T inyectDependencies(T parentObject){
		//Lista de propiedades
		Field[] campos = parentObject.getClass().getDeclaredFields();

        for (Field campo : campos) {
            //Tiene @Injected?
        	Injected injected = campo.getAnnotation(Injected.class);  
        	
            if (injected != null) {
            	Class<?> fieldClass = getFieldClass(campo); //Clase del campo

        		//Error de recursividad si una clase se intenta inyectar a si misma
            	if( fieldClass == parentObject.getClass() ) {
            		throw new IllegalArgumentException("Una clase no puede inyectarse a si misma");
            	}
            	
            	//La clase tiene @Component?
            	if(isComponent(fieldClass)) {
            		System.out.println("--Inyectando el campo '" + campo.getName() + "'");
            		
            		//TODO los set son HashSet. Deberian poder ser LinkedHashSet o TreeSet? | Implement para lists
            		if(fieldIsCollection(campo) && injected.count() >= 1 ) {//Si tiene count >= 1 y es una coleccion
            			System.out.println("--El campo '" + campo.getName() + "' es una Collection. Se van a instanciar " + injected.count() + " elementos del tipo '" + fieldClass.getSimpleName() + "'");
            			Collection<Object> fieldValue = getEmptyCollectionImplementation(campo);//
            			for(int i = 0; i < injected.count(); i++) {//Injecto un objeto en el list segun el count
            				fieldValue.add(getObjectIfSingleton(fieldClass, campo));
            			}
            			setField(parentObject, campo, fieldValue);//Le asigno el valor de la lista al campo del parentObject 
            		}
            		
            		//ARRAY
            		else if(campo.getType().isArray()) {
            			System.out.println("--El campo '" + campo.getName() + "' es un Array. Se van a instanciar " + injected.count() + " elementos del tipo '" + fieldClass.getSimpleName() + "'");           			
            			Object[] fieldValue = (Object[]) Array.newInstance(fieldClass, injected.count());
            			for(int i = 0; i < injected.count(); i++) {
            				fieldValue[i] = getObjectIfSingleton(fieldClass, campo);
            			}           			
            			setField(parentObject, campo, fieldValue);
            		}
            		
            		//OTROS CASOS
            		else {
	            		Object fieldValue = getObjectIfSingleton(fieldClass, campo);
	            		setField(parentObject, campo, fieldValue);//Le asigno al field del parentObject el fieldValue
					}
            			      
            	}
            }
        }
        
		return parentObject;
	}
	
	//Devuelve la implementacion correcta para el tipo de coleccion que sea el campo
	//La idea de ponerlo aparte en otro method es para facilitar la extension con otras implementations 
	//si hace falta(TreeSet, LinkedHashSet, otras implementations de Lists...)
	private static Collection<Object> getEmptyCollectionImplementation(Field campo) {
		return fieldIsSet(campo) ? (Set<Object>) new HashSet<Object>() : (List<Object>) new ArrayList<Object>();
	}
	
	private static <T> T getObjectIfSingleton(Class<T> fieldClass, Field campo) {
		Injected injected = campo.getAnnotation(Injected.class);
		T object = null;
		if ( injected.singleton() ) {
			object = (T) Singleton.getObjectOfClass(fieldClass);
			if ( object == null ) {
				object = getObject(fieldClass);
				Singleton.getSingletonInstances().add(object);
			}
		}
		else {
			object = getObject(fieldClass);
		}
		return object;
	}
	
	//Devuelve la clase a implementar en un campo con interface
	private static Class<?> getInterfaceImplementationClass(Class<?> interfaceClass, Injected injected){
		Reflections reflections = new Reflections(interfaceClass.getPackage().getName());
		Set<?> implementations = reflections.getSubTypesOf(interfaceClass);//Todas las clases que implementan la interface
		//System.out.println("---Implementaciones de la interface '" + interfaceClass.getSimpleName() + "': " + implementations );
		
		Class<?> implementationClass = null;//Implementacion a usar. Clase que se va a instanciar en el campo
		//TODO tirar warning si se manda implementation que no existe (exception si existe mas de una)
		//Si existe solo una implementacion usamos esa
		if(implementations.size() == 1)
			implementationClass = (Class<?>) implementations.iterator().next();//Primer item en el set
		//Si existen varias implementaciones, y se paso alguna clase por injected, usamos esa
		else if( implementations.size() > 1 && injected.implementation() != Class.class ) {
			if(implementations.contains(injected.implementation())) 
				implementationClass = injected.implementation();
		}		
		//System.out.println("---La clase a implementar es '" + implementationClass.getSimpleName() + "'");
		
    	return implementationClass;
	}
	
	//Devuelve la clase de un campo. Si es coleccion, devuelve la clase parametrizada
	private static Class<?> getFieldClass(Field campo){
		Class<?> fieldClass = campo.getType();
    	Class<?> finalClass = null;
    	if(fieldIsCollection(campo)) {
    		finalClass = getListFieldParametizedClass(campo);
    	}
    	else if (fieldClass.isArray()) {
    		finalClass = fieldClass.getComponentType();
		}
    	else if(fieldClass.isInterface() ) {
    		finalClass = getInterfaceImplementationClass(fieldClass, campo.getAnnotation(Injected.class));
    	}
    	else {
    		finalClass = fieldClass;
    	}
    	return finalClass;
	}
	
	//Devuelve la clase parametrizada en un List 
	private static Class<?> getListFieldParametizedClass(Field campo) {
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
	private static <T> void setField(T object, Field campo, Object value) {
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
	private static <T> T createObject(Class<T> objectClass) {
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
	private static <T> boolean isComponent(Class<T> classToCheck) {
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
	
	private static boolean fieldIsCollection(Field campo) {
		return Collection.class.isAssignableFrom(campo.getType());
	}
	
	private static boolean fieldIsList(Field campo) {
		return List.class.isAssignableFrom(campo.getType());
	}
	
	private static boolean fieldIsSet(Field campo) {
		return Set.class.isAssignableFrom(campo.getType());
	}
	
}
