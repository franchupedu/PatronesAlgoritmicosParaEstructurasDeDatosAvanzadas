package test;

import dependencyInjection.*;

@Component
public class TheBeatles implements Banda
{
   @Injected(implementation=GeorgeHarrison.class)
   private Guitarrista primeraGuitarra;

   @Injected(implementation=JohnLennon.class)
   private Guitarrista segundaGuitarra;

   @Injected
   private Bajista bajista;

   @Injected
   private Baterista baterista;

   public String toString()
   {
      String ret = "";
      ret+="The Beatles { \n";
      ret+="       "+getPrimeraGuitarra()+"\n";
      ret+="      ,"+getSegundaGuitarra()+"\n";
      ret+="      ,"+getBajista()+"\n";
      ret+="      ,"+baterista+" }\n";
      return ret;
   }

public Bajista getBajista() {
	return bajista;
}

public void setBajista(Bajista bajista) {
	this.bajista = bajista;
}

public Guitarrista getPrimeraGuitarra() {
	return primeraGuitarra;
}

public void setPrimeraGuitarra(Guitarrista primeraGuitarra) {
	this.primeraGuitarra = primeraGuitarra;
}

public Guitarrista getSegundaGuitarra() {
	return segundaGuitarra;
}

public void setSegundaGuitarra(Guitarrista segundaGuitarra) {
	this.segundaGuitarra = segundaGuitarra;
}
}
