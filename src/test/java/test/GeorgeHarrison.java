package test;

import dependencyInjection.*;

@Component
public class GeorgeHarrison implements Guitarrista
{
   @Override
   public String toString()
   {
      return "George Harrison";
   }

}
