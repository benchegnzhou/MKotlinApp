package zbc.com.calclib;

import java.util.ArrayList;

class SamInJava {

    private ArrayList<Runnable> list = new ArrayList<>();

    public void addTask(Runnable runnable) {
        list.add(runnable);
        System.out.println("After add:"+runnable+", We hava "+list.size()+"in all.");
    }

    public void removeTask(Runnable runnable) {
        list.remove(runnable);
        System.out.println("After remove:"+runnable+", We hava "+list.size()+"in left.");
    }


}
