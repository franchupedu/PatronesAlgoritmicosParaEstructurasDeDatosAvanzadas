package demo;

import dependencyInjection.Component;

@Component
public class JohnLennon implements Guitarrista
{
   @Override
   public String toString()
   {
      return "John Lennon";
   }

}
