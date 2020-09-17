package zbc.com.cn.list;

interface List<E> {

    /**
     * 清除元素
     */
    void clear();

    /**
     * 元素数量
     *
     * @return
     */
    int size();

    /**
     * 是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 添加元素
     */
    boolean add(E element);

    /**
     * 获取元素
     */
    E get(int index);

    /**
     * 未指定位置设置元素
     */
    boolean set(int index, E element);

    /**
     * 在index位置插入一个元素
     */
    boolean add(int index, E element);


    /**
     * 删除index位置的元素
     *
     * @param index
     */
    boolean remove(int index);


    /**
     * 查看元素索引
     *
     * @param element
     * @return
     */
    int indexOf(E element);

}
