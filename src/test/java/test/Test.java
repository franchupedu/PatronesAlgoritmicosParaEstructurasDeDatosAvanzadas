package test;

import org.junit.Assert;

import dependencyInjection.*;

public class Test
{
   @org.junit.Test
   public void test1()
   {
      Banda banda = Factory.getObject(TheBeatles.class);
      Assert.assertTrue(banda.getClass().equals(TheBeatles.class));
      Assert.assertTrue(true);
   }
   
   @org.junit.Test
   public void test2()
   {
      TheBeatles banda = Factory.getObject(TheBeatles.class);
      Assert.assertTrue(banda.getBajista().getClass().equals(PaulMcCartney.class));
   }
   
   @org.junit.Test
   public void test3()
   {
      TheBeatles banda = Factory.getObject(TheBeatles.class);
      Assert.assertTrue(banda.getPrimeraGuitarra().getClass().equals(GeorgeHarrison.class));
   }

}
