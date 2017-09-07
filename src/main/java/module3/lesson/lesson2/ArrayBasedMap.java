package module3.lesson.lesson2;

import java.util.*;

/*
"map"

Сегодня нам предстоит поработать. Дело в том, что мы будем реализовывать с Вами интерфейс Map! На базе списка пар. Возможно, Вы подумаете, что список пар лучше заменить на Set пар, и будете правы! Но так как тему Set мы еще не трогали (и позже станет понятно почему), то остановимся на списке.

В Вашем распоряжении есть список пар, каждая пара представлена в виде объекта типа ArrayBasedMap.Pair

Ваша задача реализовать следующие методы интерфейса Map:

size()
isEmpty()
containsKey(Object key)
containsValue(Object value)
get(Object key) {
put(K key, V value)
remove(Object key)
clear()
values()
Некоторые методы уже реализованы за Вас, например:

entrySet() - мы еще не рассматривали тему Set
keySet() - по той же причине
putAll(Map<? extends K, ? extends V> m) - требует немного магии Generics
О том, как будем тестировать

Тестирование Вашего ответа будет проводиться специальным тестом, разработанным в Google. Дело в том, что у них есть
 своя библиотека, которая реализует основные коллекции (так что не только Вы занимаетесь полной реализаций всех
  популярных коллекций с нуля;) ). Для тестирования того, что коллекция работает корректно, у них существуют Unit
   тесты, один из которых с небольшими изменениями и будет тестировать корректность Вашей реализации.

Оригинальный тест можно найти вот тут.

Детали реализации методов

Все методы должны быть реализованы в соответствии с официальной документацией.

!!! Этот раздел стоит читать только, если не понятно как реализовывать методы.

size() - просто возвращаем длину листа всех пар
isEmpty() - проверяем, не равна ли длинна нулю
containsKey(Object key) - обходим весь лист пар и, если хоть в одной паре у нас ключ равен аргументу метода,
 то возвращаем true
containsValue(Object value) - как и containsKey, только вместо ключа проверяем значение
get(Object key) - обходим все пары и возвращаем значение у пары, которая содержит заданный ключ
put(K key, V value) - проверяем, есть ли пара с заданным ключом; если есть - перезаписываем значение, если нету -
создаем новую пару (не забываем о том, что нам нужно возвратить из метода согласно документации;) )
remove(Object key) - удаляем пару с заданным ключем
clear() - сносим все в нашем списке
values() - создаем лист всех значений всех пар
Ну что ж, задача сложная, да и проверка ОЧЕНЬ скрупулезная, так что остается пожелать Вам удачи в выполнении задания!
 */
public class ArrayBasedMap<K, V> implements Map<K, V> {

    private List<Pair> values = new ArrayList<Pair>();

    @Override
    public int size() {
        // BEGIN (write your solution here)
        return values.size();
        // END
    }

    @Override
    public boolean isEmpty() {
        // BEGIN (write your solution here)
        return this.size() == 0;
        // END
    }

    @Override
    public boolean containsKey(Object key) {
        // BEGIN (write your solution here)
        for (int i =0; i < this.size(); i++) {
            Pair pair = values.get(i);
            if (pair.getKey().equals(key)) {
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public boolean containsValue(Object value) {
        // BEGIN (write your solution here)
        for (Pair pair : values) {
            if (pair.getValue().equals(value)) {
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public V get(Object key) {
        // BEGIN (write your solution here)
        for (int i =0; i < values.size(); i++) {
            Pair pair = values.get(i);
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
        // END
    }

    @Override
    public V put(K key, V value) {
        // BEGIN (write your solution here)
        if (!containsKey(key)) {
            values.add(new Pair(key, value));
            return null;
        }
        for (int i = 0; i < size(); i++) {
            Pair pair = values.get(i);
            if (pair.getKey().equals(key)) {
                values.set(i, new Pair(key, value));
                return pair.getValue();
            }
        }
        return null;
        // END
    }

    @Override
    public V remove(Object key) {
        // BEGIN (write your solution here)
        for (int i = 0; i < size(); i++) {
            Pair pair = values.get(i);
            if (pair.getKey().equals(key)) {
                values.remove(i);
                return pair.getValue();
            }
        }
        return null;
        // END
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<K, V> e : (Set<Map.Entry<K, V>>)(Set)m.entrySet())
            put(e.getKey(), e.getValue());
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        values = new ArrayList<Pair>();
        // END
    }

    @Override
    public Set<K> keySet() {
        final Set<K> keys = new HashSet<K>();
        for (Pair p : values) keys.add(p.getKey());
        return keys;
    }

    @Override
    public Collection<V> values() {
        // BEGIN (write your solution here)
        final List<V> value = new ArrayList<>();
        for (Pair pair : values) {
            value.add(pair.getValue());
        }
        return value;
        // END
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return (Set<Entry<K, V>>)(Set)new HashSet<>(values);
    }

    private class Pair implements Map.Entry<K, V> {

        private final K key;

        private V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            final V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            Map.Entry<K, V> pair = (Map.Entry<K, V>) o;


            if (key != null ? !key.equals(pair.getKey()) : pair.getKey() != null) return false;
            return !(value != null ? !value.equals(pair.getValue()) : pair.getValue() != null);

        }

        @Override
        public int hashCode() {
            return (key   == null ? 0 :   key.hashCode()) ^
                    (value == null ? 0 : value.hashCode());
        }
    }
}
