package test;

import dependencyInjection.*;

@Component
public class PaulMcCartney implements Bajista
{
   @Override
   public String toString()
   {
      return "Paul McCartney";
   }

}
