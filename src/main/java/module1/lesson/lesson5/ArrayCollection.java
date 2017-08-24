package module1.lesson.lesson5;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Iterator;

/*
"Класс AbstractCollection"

В этом практическом задании Вам необходимо реализовать интерфейс Iterator. Внимательно ознакомьтесь с документацией по интерфейсу Iterator. Подобное задание в упрощенном виде уже предлагалось к выполнению ранее, однако тогда не было требования реализовать метод удаления и не проверялись исключения. Сегодня Вам предстоит реализовать полноценный итератор на 100% соотвествующий документации.

Реализовать необходимо методы (в точности так, как описано в документации):

boolean hasNext()
E next()
void remove()
Корректность Вашей реализации будет проверена Unit тестами, которые Вы можете найти в классе ArrayCollectionTest.

Имя класса, в котором Вам необходимо написать реализацию: ArrayCollection

При возникновении проблем с переводом документации можете воспользоваться переведенной версией.

Особое внимание нужно уделить исключениям, которые бросают методы!
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
    public <T1> T1[] toArray(T1[] a) {
        return (T1[])this.toArray();
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
        if (this.size() != 0) { //fix
            size--;
        }
    }

    private class ElementsIterator implements Iterator<T> {
        // BEGIN (write your solution here)
        private int index;
        private int counterForRemove;

        public boolean hasNext() {
            return ArrayCollection.this.size > index;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return ArrayCollection.this.m[index++];
        }

        public void remove() throws IllegalStateException {
            counterForRemove++;

            if (index < counterForRemove) {
                throw new IllegalStateException();
            }

            ArrayCollection.this.remove(index - 1);
            index--;
            counterForRemove--;
        }
        // END
    }
}
