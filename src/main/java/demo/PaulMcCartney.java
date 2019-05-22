package demo;

import dependencyInjection.Component;

@Component
public class PaulMcCartney implements Bajista
{
   @Override
   public String toString()
   {
      return "Paul McCartney";
   }

}
