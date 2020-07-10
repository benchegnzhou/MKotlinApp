package zbc.com.calclib;

import zbc.com.calclib.reflections.Demo;
import zbc.com.calclib.reflections.Person3;
import zbc.com.calclib.reflections.Person4;
import zbc.com.calclib.reflections.Person5;

public class DemoReflections {

    public static void main(String[] args) {
        try {

            Demo.class.getDeclaredMethod("sayHello", Person3.class, String.class)
                    .invoke(null, new Person3("liushuai", 24), "Leeli");
        } catch (Exception e) {
            e.printStackTrace();
        }


//        JavaReflectionsKt.Hello(new Person4());
        Demo.Hello(new Person4());
        Person5 person5 = new Person5("Leeli", 1);
        Person5 p = person5.plus(new Person5("Lie", 20));
        System.out.println(p);
    }
}
