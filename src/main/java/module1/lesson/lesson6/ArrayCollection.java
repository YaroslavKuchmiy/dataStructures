package module1.lesson.lesson6;


import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Iterator;

/*
"toArray"

В качестве "выпускного" теста к модулю 1, Вашему вниманию будет предложена задача имплеминтации всего одного метода,
 который мы не рассматривали в теории и не использовали ранее совсем. Этот метод:

T[] toArray(T[] a)

детальное описание метода можно найти в документации.

Рекомендуется выполнять это задание максимально самостоятельно, однако, если возникнут трудности в качестве подсказки
 можно воспользоваться кодом метода из самого JDK
 */
public class ArrayCollection<T> implements Collection<T> {

    private T[] m = (T[])new Object[1];

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        for (int i = 0; i < size; i++) {
            if (m[i].equals(o)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        final T[] newM = (T[])new Object[this.size()];
        System.arraycopy(m, 0, newM, 0, this.size());
        return newM;
    }

    @Override
    public <T1> T1[] toArray(final T1[] a) {
        // BEGIN (write your solution here)
        if (this.size <= a.length) {
            System.arraycopy(m, 0, a, 0, this.size());
            return a;
        }

        else {
            final T1[] newM = Arrays.copyOf(a, this.size());
            System.arraycopy(m, 0, newM, 0, this.size());
            return newM;
        }
        // END
    }

    @Override
    public boolean add(final T t) {
        if (m.length == size) {
            final T[] oldM = m;
            m = (T[]) new Object[this.size() * 2];
            System.arraycopy(oldM, 0, m, 0, oldM.length);
        }
        m[size++] = t;
        return true;
    }

    @Override
    public boolean remove(final Object o) {
        for (int i = 0; i < size(); i++) {
            if (m[i].equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final Object item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        m = (T[])new Object[1];
        size = 0;
    }

    private void remove(final int index) {
        if (index != this.size() - 1)
            System.arraycopy(m, index + 1, m, index, this.size() - index - 1);
        size--;
    }

    private class ElementsIterator implements Iterator<T> {

        private int index;

        private int last = -1;

        @Override
        public boolean hasNext() {
            return ArrayCollection.this.size() > index;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            last = index;
            return ArrayCollection.this.m[index++];
        }

        public void remove() {
            if (last == -1)
                throw new IllegalStateException();
            ArrayCollection.this.remove(last);
            index--;
            last = -1;
        }

    }


}

