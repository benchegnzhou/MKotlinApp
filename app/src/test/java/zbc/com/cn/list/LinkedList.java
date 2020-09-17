package zbc.com.cn.list;


import android.util.Log;

import androidx.annotation.NonNull;


class LinkedList<E> extends AbstractList<E> {

    private Node firstNode;

    @Override
    public void clear() {
        firstNode = null;
        size = 0;
    }


    @Override
    public boolean add(E element) {

        return add(size, element);
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public boolean set(int index, E element) {
        Node<E> node = node(index);
        node.element = element;
        return true;
    }

    @Override
    public boolean add(int index, E element) {

        //注意监测边界位置
        if (index == 0) {
            Node<E> objectNode = new Node<>();
            objectNode.next = null;
            objectNode.element = element;
            firstNode = objectNode;
        } else {
            Node<E> prev = node(index - 1);
            Node<E> objectNode = new Node<>();
            try {
                objectNode.next = node(index);
            } catch (Exception e) {
                objectNode.next = null;
            }

            objectNode.element = element;
            prev.next = objectNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(int index) {
        if (index == 0) {
            firstNode = firstNode.next;
            size--;
        } else {
            Node<E> node = node(index - 1);
            //将连接剔除index位置的元素
            node.next = node.next.next;
            size--;
        }
        return true;
    }

    @Override
    public int indexOf(E element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node(i).element == null) {
                    return i;
                }
            }
            return -1;
        } else {
            int index = -1;
            for (int i = 0; i < size; i++) {
                Node<E> node = node(i);
                if (element.equals(node.element)) {
                    index = i;
                    break;
                }
            }
            return index;
        }
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            Log.e("finalize", "Node被销毁了");
        }
    }


    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index:" + index + "Size:" + size);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("size = ").append(size).append(" ,");
        builder.append("[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                builder.append(",");
            }
            builder.append(get(i));
        }
        builder.append("]");
        return builder.toString();
    }


    /**
     * 获取节点
     *
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = firstNode;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }
}
