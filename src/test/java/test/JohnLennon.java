package test;

import dependencyInjection.*;

@Component
public class JohnLennon implements Guitarrista
{
   @Override
   public String toString()
   {
      return "John Lennon";
   }

}
