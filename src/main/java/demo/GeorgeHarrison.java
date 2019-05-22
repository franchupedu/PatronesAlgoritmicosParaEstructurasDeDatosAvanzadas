package demo;

import dependencyInjection.Component;

@Component
public class GeorgeHarrison implements Guitarrista
{
   @Override
   public String toString()
   {
      return "George Harrison";
   }

}
