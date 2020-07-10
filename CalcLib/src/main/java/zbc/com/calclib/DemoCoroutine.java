package zbc.com.calclib;

import org.jetbrains.annotations.NotNull;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;



public class DemoCoroutine {

    public static void main(String[] args) {

        Object userCoroutine = ExceptionCoroutineKt.getUserCoroutine(new Continuation<UserBean>() {
            @NotNull
            @Override
            public CoroutineContext getContext() {
                return null;
            }

            @Override
            public void resumeWith(@NotNull Object o) {
                System.out.println(o);
            }
        });
//        String name = userBean.getName();
//        int age = userBean.getAge();
    }
}


