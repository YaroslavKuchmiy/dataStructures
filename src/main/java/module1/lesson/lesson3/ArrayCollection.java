package module1.lesson.lesson3;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArrayCollection<T> implements Collection<T> {

    private T[] m = (T[])new Object[1];

    private int size;

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return this.size;
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return this.size == 0;
        // END
    }

    @Override
    public boolean contains(final Object o) {
        // BEGIN (write your solution here)
        for (Object element: this.m) {
            if (element.equals(o)) {
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public Iterator<T> iterator() {
        // BEGIN (write your solution here)
        return new ElementsIterator();
        // END
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)
        final T[] newM = (T[]) new Object[this.size];
        System.arraycopy(m, 0, newM, 0, this.size);
        return newM;
        // END
    }

    @Override
    /*This method may prove to be too difficult.
    he test is not covered.*/
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        return (T1[])this.toArray();
        // END
    }

    @Override
    public boolean add(final T t) {
        // BEGIN (write your solution here)
        if (m.length == size) {
            T[] oldM = m;
            m = (T[]) new Object[size * 2];
            System.arraycopy(oldM, 0, m, 0, oldM.length);
        }
        m[size++] = t;
        return true;
        // END
    }

    @Override
    public boolean remove(final Object o) {
        // BEGIN (write your solution here)
        for (int i = 0; i < this.size; i++) {
            if (this.m[i].equals(o)) {
                if (i != this.size - 1) {
                    System.arraycopy(m, i + 1, m, i, this.size - i -1);
                }
                size--;
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        for (Object element: c) {
            if (!this.contains(element)) {
                return false;
            }
        }
        return true;
        // END
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        // BEGIN (write your solution here)
        for (T element: c) {
            this.add(element);
        }
        return true;
        // END
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        for (Object element: c) {
            this.remove(element);
        }
        return true;
        // END
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        // BEGIN (write your solution here)
        for (int i = 0; i < size(); i++) {
            if (!c.contains(m[i])) {
              /*Счетчик цикла необходимо уменьшать при каждом удалении.
              Потому что удаление элемента из коллекции не просто уменшает size,
               а еще и сдвигает элементы в массиве.*/
                remove(m[i--]);
            }
        }
        return true;
        // END
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        this.m = (T[])new Object[1];
        this.size = 0;
        // END
    }

    private class ElementsIterator implements Iterator<T> {
        // BEGIN (write your solution here)
        private int index = 0;

        @Override
        public boolean hasNext() {
            return ArrayCollection.this.size() > index;
        }

        @Override
        public T next() {
            return ArrayCollection.this.m[index++];
        }
        // END
    }

}

