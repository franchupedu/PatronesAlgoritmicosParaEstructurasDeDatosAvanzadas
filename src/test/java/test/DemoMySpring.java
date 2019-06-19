package test;

import dependencyInjection.*;

//@ComponentScan("frm.myspring.demo")
public class DemoMySpring
{
   public static void main(String[] args)
   {
      Banda banda = Factory.getObject(TheBeatles.class);
      System.out.println(banda);
   }
}
