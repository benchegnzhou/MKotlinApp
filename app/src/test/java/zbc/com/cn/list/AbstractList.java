package zbc.com.cn.list;

abstract class AbstractList<E> implements List<E>{
    protected int size;


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
