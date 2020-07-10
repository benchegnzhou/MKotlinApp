package zbc.com.calclib;


import zbc.com.calclib.sam.Item;
import zbc.com.calclib.sam.JavaInterface;

public class DemoSAM {

    Item item = new Item();


    String delegateWork(JavaInterface javaInterface) {
        return javaInterface.doSomething(item);
    }



    public static void main(String[] args) {
        String msg = new DemoSAM().delegateWork(item -> item.getClass().toString());
        System.out.println(msg);
    }
}
