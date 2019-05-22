package demo;

import dependencyInjection.Component;

@Component
public class RingoStarr implements Baterista
{
   @Override
   public String toString()
   {
      return "Ringo Starr";
   }

}
