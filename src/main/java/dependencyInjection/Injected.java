package dependencyInjection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Injected {
	int count() default 1;
	boolean singleton() default false;
	Class<?> implementation() default Class.class; //TODO 
}
