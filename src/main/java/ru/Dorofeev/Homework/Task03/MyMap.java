package ru.Dorofeev.Homework.Task03;

import java.lang.reflect.Array;
import java.util.*;

public class MyMap<K,V> implements Map {
    private Bucket[] buckets;

    public MyMap() {
        this.buckets = (Bucket[]) Array.newInstance(Bucket.class,16);
    }
    public MyMap(int buckets) {
        this.buckets = (Bucket[]) Array.newInstance(Bucket.class,buckets);
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
        if (buckets[hash] == null) throw new NoSuchElementException();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (o.hashCode() != this.hashCode()) return false;
        MyMap<K, V> myMap = (MyMap<K, V>) o;
        Set<Entry> set1 = this.entrySet();
        Set<Entry> set2 = myMap.entrySet();
        return set1.equals(set2);
    }

    @Override
    public int hashCode() {
        Set<Entry> set = this.entrySet();
        int result = 0;
        for (Entry entry:set) {
            result += Objects.hashCode(entry);
        }
        return result;
    }
}
