package stc21;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

// TODO VisualVM

public class MyMap<K,V> {
    private MapManager[] buckets;
    private int size;
    ArrayList l = new ArrayList();

    public MyMap() {
        this.buckets = (MapManager[]) Array.newInstance(MapManager.class,16);
        this.size = 0;
    }

    public MyMap(int buckets) {
        this.buckets = (MapManager[]) Array.newInstance(MapManager.class,buckets);
        this.size = 0;
    }

    private int getHash(K key) {
        return Objects.hash(key)%buckets.length;
    }

    public int size() {
        size = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) continue;
            size+=buckets[i].getSize();
        }
        return size;
    }
//666
    public V put(K key, V value){
        int hash = getHash(key);
        if (buckets[hash] == null) buckets[hash] = new MapManager(key.getClass(),value.getClass());
        return (V) buckets[hash].addElement(key,value);
    }

    public V updateElement(K key,V value){
        int hash = getHash(key);
        if (buckets[hash] == null) throw new NoSuchElementException();
        return (V) buckets[hash].addElement(key,value);
    }

    public V remove(K key){
        int hash = getHash(key);
        if (buckets[hash] == null) throw new NoSuchElementException();
        return (V) buckets[hash].removeElement(key);
    }

    public V get(K key){
        int hash = getHash(key);
        if (buckets[hash] == null) throw new NoSuchElementException();
        return (V) buckets[hash].findElementByKey(key);
    }

}
