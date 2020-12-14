import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import static org.junit.Assert.*;

public class MyHashMapTest {

    @Test
    public void size() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        HashMap<Integer, String> hashMap = new HashMap<>();
        myHashMap.put(1, "Vadim");
        hashMap.put(2, "Alena");
        myHashMap.put(1, "Vadim");
        hashMap.put(2, "Alena");
        assertEquals(hashMap.size(), myHashMap.size());
    }

    @Test
    public void containsKey() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        HashMap<Integer, String> hashMap = new HashMap<>();

        myHashMap.put(1321, "Vadim");
        hashMap.put(1321, "Vadim");
        myHashMap.put(2113, "Alena");
        hashMap.put(2113, "Alena");

        assertEquals(hashMap.containsKey(1321), myHashMap.containsKey(1321));
        assertEquals(hashMap.containsKey(2113), myHashMap.containsKey(2113));
    }

    @Test
    public void get() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        HashMap<Integer, String> hashMap = new HashMap<>();

        myHashMap.put(1321, "Vadim");
        myHashMap.put(2113, "Alena");
        hashMap.put(1321, "Vadim");
        hashMap.put(2113, "Alena");

        assertEquals(hashMap.get(1), myHashMap.get(1));
        assertEquals(hashMap.get(2), myHashMap.get(2));
    }

    @Test
    public void remove() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        myHashMap.put(1321, "Vadim");
        myHashMap.put(2113, "Alena");
        myHashMap.put(1316, "Angel");

        myHashMap.remove(2);
        assertNull(myHashMap.get(2));
    }

    @Test
    public void keySet() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        for (int i = 0; i < 10; i++) {
            myHashMap.put(i, String.valueOf(i));
        }

        Set<Integer> set = myHashMap.keySet();

        for (int i = 0; i < 10; i++) {
            assertTrue(set.contains(i));
        }
    }

    @Test
    public void values() {
        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();

        for (int i = 0; i < 10; i++) {
            myHashMap.put(i, String.valueOf(i));
        }

        Collection<String> list = myHashMap.values();

        for (int i = 0; i < 10; i++) {
            assertTrue(list.contains(String.valueOf(i)));
        }
    }

}
