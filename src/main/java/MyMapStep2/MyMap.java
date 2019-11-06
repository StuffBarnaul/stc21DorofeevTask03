package MyMapStep2;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;



public class MyMap<K,V> implements Map {
    private Bucket[] buckets;
    private double loadFactor;

    MyMap() {
        this.buckets = (Bucket[]) Array.newInstance(Bucket.class,2);
        this.loadFactor = 0.75;
    }

    MyMap(int bucketSize, double loadFactor) {
        this.buckets = (Bucket[]) Array.newInstance(Bucket.class,bucketSize);
        this.loadFactor = loadFactor;
    }

    private int getHash(K key) {
        return Objects.hash(key)%buckets.length;
    }

    public int size() {
        int size = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) continue;
            size+=buckets[i].getSize();
        }
        return size;
    }

    public V put(Object key, Object value){
        int loadedBuckets = 0;
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) loadedBuckets++;
        }
        if (((double)loadedBuckets / buckets.length) > loadFactor) {
            Set<Entry> entries = entrySet();
            this.buckets = (Bucket[]) Array.newInstance(Bucket.class,buckets.length*2);
            Map map = new HashMap();
            for (Entry entry: entries) {
                map.put(entry.getKey(),entry.getValue());
            }
            this.putAll(map);
        }
        int hash = getHash((K) key);
        if (buckets[hash] == null){
            if (key == null && value == null) buckets[hash] = new Bucket(Object.class,Object.class);
            if (key == null && value != null) buckets[hash] = new Bucket(Object.class,value.getClass());
            if (key != null && value == null) buckets[hash] = new Bucket(key.getClass(),Object.class);
            if (key != null && value != null) buckets[hash] = new Bucket(key.getClass(),value.getClass());
        }
        return (V) buckets[hash].addElement(key,value);
    }

    public V remove(Object key){
        int hash = getHash((K) key);
        if (buckets[hash] == null) return null;
        return (V) buckets[hash].removeElement(key);
    }

    public V get(Object key){
        int hash = getHash((K) key);
        if (buckets[hash] == null) throw new NoSuchElementException();
        return (V) buckets[hash].findElementByKey(key);
    }

    public boolean containsKey(Object key){
        int hash = getHash((K) key);
        if (buckets[hash] == null) return false;
        return buckets[hash].containsKey(key);
    }

    public boolean containsValue(Object value){
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null && buckets[i].containsValue(value)) return true;
        }
        return false;
    }

    @Override
    public void putAll(Map map) {
        for(Object data:map.keySet()){
            K key = (K) data;
            V value = (V) map.get(key);
            int hash = getHash(key);
            if (buckets[hash] == null) buckets[hash] = new Bucket(key.getClass(),value.getClass());
            buckets[hash].addElement(key,value);
        }
    }

    @Override
    public Set keySet() {
        Set result = new HashSet();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) continue;
            result.addAll(Arrays.asList(buckets[i].getKeys()));
        }
        return result;
    }

    @Override
    public Collection<V> values() {
        Collection result = new ArrayList();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] == null) {
            } else result.addAll(Arrays.asList(buckets[i].getValues()));
        }
        return result;
    }

    @Override
    public Set<Entry> entrySet() {
        Set<Entry> entrySet = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (int j = 0; j < buckets[i].getSize(); j++) {
                    K key = (K) buckets[i].getKeys()[j];
                    V value = (V) buckets[i].getValues()[j];
                    Entry entry = new AbstractMap.SimpleEntry(key,value);
                    entrySet.add(entry);
                }
            }
        }
        return entrySet;
    }

    @Override
    public boolean isEmpty() {
        if (size() == 0) return true;
        else return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new Bucket<>(Object.class,Object.class);
        }
    }
}
