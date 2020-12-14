import java.util.*;

public class MyHashMap<K, V> implements Map<K, V> {

    private int dimensionsOfArray;
    public int elements;
    private MyNode<K, V>[] container;

    public MyHashMap(int dimensionsOfArray) {
        elements = 0;
        this.dimensionsOfArray = 0;
        container = (MyNode<K, V>[]) new MyNode[dimensionsOfArray];
    }

    public MyHashMap() {
        this(16);
    }

    class MyNode<K, V> implements Map.Entry<K, V>{
        final int hash;
        final K key;
        V value;
        MyNode<K, V> next;

        public MyNode(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = null;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }

        public final boolean equals(Object o) {
            if (o == this) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyNode<?, ?> myNode = (MyNode<?, ?>) o;
            return Objects.equals(key, myNode.key) && Objects.equals(value, myNode.value);
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Override
    public V get(Object key) {
        int hashCode = hash(key);
        int index = hashCode & (container.length - 1);

        MyNode<K, V> begin = container[index];
        while (true) {
            if (begin == null) {
                return null;
            } else if (Objects.equals(begin.key, key)) {
                return begin.value;
            } else begin = begin.next;
        }
    }

    @Override
    public int size() {
        return elements;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public V put(Object key, Object value) {
        int hashCode = hash(key);
        MyNode<K, V> myNode = (MyNode) new MyNode<>(hashCode, key, value);
        int index = hashCode & (container.length - 1);
        if (container[index] == null) {
            container[index] = myNode;
            elements++;
            dimensionsOfArray++;
            if (container.length == dimensionsOfArray) {
                resize();
            }
            return null;
        } else {
            MyNode<K, V> bufferMyNode = container[index];
            while (true) {
                if (Objects.equals(bufferMyNode.key, myNode.key)) {
                    V oldValue = bufferMyNode.value;
                    bufferMyNode.value = myNode.value;
                    return oldValue;
                } else if (bufferMyNode.next == null) {
                    bufferMyNode.next = myNode;
                    resize();
                    elements++;
                    return null;
                } else bufferMyNode = bufferMyNode.next;
            }
        }
    }

    public void resize() {
        elements = 0;
        int newDimensionsOfArray = container.length * 2;
        MyNode<K, V>[] bufferContainer = container;
        container = (MyNode<K, V>[]) new MyNode[newDimensionsOfArray];
        for (int i = 0; i < bufferContainer.length; i++) {
            MyNode<K, V> bufferMyNode = bufferContainer[i];
            while (bufferMyNode != null) {
                put(bufferMyNode.key, bufferMyNode.value);
                bufferMyNode = bufferMyNode.next;
            }
        }
    }

    @Override
    public V remove(Object key) {
        int hashCode = hash(key);
        int index = hashCode & (container.length - 1);
        MyNode<K, V> actual = container[index];
        while (true) {
            if (actual == null) {
                return null;
            } else if (Objects.equals(actual.key, key)) {
                V oldValue = actual.value;
                container[index] = container[index].next;
                return oldValue;
            } else {
                actual = actual.next;
            }
            elements--;
        }
    }

    @Override
    public Set keySet() {
        HashSet<K> kSet = new HashSet<>();
        for (int i = 0; i < container.length; i++) {
            if (container[i] != null) {
                MyNode<K, V> buf = container[i];
                while (buf != null) {
                    kSet.add(buf.key);
                    buf = buf.next;
                }
            }
        }
        return kSet;
    }

    @Override
    public Collection values() {
        ArrayList<V> valuesAL = new ArrayList<>();
        for (int i = 0; i < container.length; i++) {
            if (container[i] != null) {
                MyNode<K, V> buf = container[i];
                while (buf != null) {
                    valuesAL.add(container[i].value);
                    buf = buf.next;
                }
            }
        }
        return valuesAL;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<>();
        for (int i = 0; i < container.length; i++) {
            if (container[i] != null) {
                MyNode<K, V> bufMyNode = container[i];
                while (bufMyNode != null) {
                    entries.add(bufMyNode);
                    bufMyNode = bufMyNode.next;
                }
            }
        }
        return entries;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Не реализовано");
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Не реализовано");
    }

    @Override
    public void putAll(Map m) {
        throw new UnsupportedOperationException("Не реализовано");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Не реализовано");
    }
}