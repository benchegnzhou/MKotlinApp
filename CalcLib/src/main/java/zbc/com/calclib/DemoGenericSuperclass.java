package zbc.com.calclib;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import kotlin.experimental.ExperimentalTypeInference;

class DemoGenericSuperclass {
    class Person<T, V> {
    }

    class Teacher {
    }

    class Student extends Person<Student, Teacher> {
    }


    public static void main(String[] args) {
        Type aClass = Student.class.getSuperclass();
        Type genericSuperclass = Student.class.getGenericSuperclass();
        System.out.println(aClass);
        System.out.println(genericSuperclass);

        Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        for (Type type : actualTypeArguments) {
            System.out.println(type);
        }
        System.out.println(actualTypeArguments[0]);
    }
}


