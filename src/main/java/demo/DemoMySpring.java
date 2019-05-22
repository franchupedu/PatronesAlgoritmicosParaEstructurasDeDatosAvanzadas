package demo;

import dependencyInjection.Factory;

public class DemoMySpring
{
   public static void main(String[] args)
   {
      Banda banda = Factory.getObject(TheBeatles.class);
      System.out.println(banda);
   }
}
