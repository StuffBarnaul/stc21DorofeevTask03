package ru.Dorofeev.Homework.Task03;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.Assert.*;

public class MyMapTest {
    private MyMap map = new MyMap(16);
    private HashMap hashmap = new HashMap();
    private String s1 = "s1";
    private String s2 = "s2";
    private String s3 = "s3";
    private String s4 = "s4";
    private int i1 = 1;
    private int i2 = 2;
    private int i3 = 3;
    private int i4 = 4;

    @Before
    public void setUp(){
        map.put(s1,i1);
        map.put(s2,i2);
        map.put(s3,i3);
        map.put(s4,i4);

        hashmap.put(s1,i1);
        hashmap.put(s2,i2);
        hashmap.put(s3,i3);
        hashmap.put(s4,i4);
    }

    @After
    public void tearDown(){
        int i=0;
    }

    @Test
    public void size() {
        assertEquals(map.size(),hashmap.size());
        assertEquals(map.put("qwe",10),hashmap.put("qwe",10));
        assertEquals(map.put("asd",123456),hashmap.put("asd",123456));
        assertEquals(map.size(),hashmap.size());
        assertEquals(map.remove(s1),hashmap.remove(s1));
        assertEquals(map.remove(s2),hashmap.remove(s2));
        assertEquals(map.size(),hashmap.size());
    }

    @Test
    public void put() {
        assertEquals(map.put("qwe",10),hashmap.put("qwe",10));
        assertEquals(map.put("asd",123456),hashmap.put("asd",123456));
        assertEquals(map.put("asd",123),hashmap.put("asd",123));
        assertEquals(map.put(null,null),hashmap.put(null,null));
        assertEquals(map.put(null,10),hashmap.put(null,10));
        assertEquals(map.put(null,null),hashmap.put(null,null));
        assertEquals(map.put("zxc",123456),hashmap.put("zxc",123456));
        assertEquals(map.put("as",123),hashmap.put("as",123));
        assertEquals(map.size(),hashmap.size());
    }

    @Test(expected = ArrayStoreException.class)
    public void addIllegalElement() {
        assertEquals(map.put(1,10),null);
    }

    @Test
    public void remove() {
        assertEquals(map.remove(s1),hashmap.remove(s1));
        assertEquals(map.remove(s2),hashmap.remove(s2));
        assertEquals(map.remove(s3),hashmap.remove(s3));
        assertEquals(map.remove(s4),hashmap.remove(s4));
        assertEquals(map.size(),hashmap.size());
        assertEquals(map.put(null,10),hashmap.put(null,10));
        assertEquals(map.remove(null),hashmap.remove(null));
        assertEquals(map.size(),hashmap.size());
        assertEquals(map.put("qwe",10),hashmap.put("qwe",10));
        assertEquals(map.put("asd",123456),hashmap.put("asd",123456));
        assertEquals(map.size(),hashmap.size());
        assertEquals(map.containsKey(null),hashmap.containsKey(null));
        assertEquals(map.containsKey("qwe"),hashmap.containsKey("qwe"));
        assertEquals(map.get("qwe"),hashmap.get("qwe"));
    }

    @Test
    public void get() {
        assertEquals(map.get(s1),hashmap.get(s1));
        assertEquals(map.put(null,null),hashmap.put(null,null));
        assertEquals(map.get(null),hashmap.get(null));
        assertEquals(map.put(null,10),hashmap.put(null,10));
        assertEquals(map.get(null),hashmap.get(null));
        assertEquals(map.put(null,null),hashmap.put(null,null));
        assertEquals(map.get(null),hashmap.get(null));
        assertEquals(map.put("qwe",10),hashmap.put("qwe",10));
        assertEquals(map.put("asd",123456),hashmap.put("asd",123456));
        assertEquals(map.containsKey(null),hashmap.containsKey(null));
        assertEquals(map.containsKey("qwe"),hashmap.containsKey("qwe"));
    }

    @Test
    public void containsKey(){
        assertEquals(map.containsKey(s1),hashmap.containsKey(s1));
        assertEquals(map.put(null,null),hashmap.put(null,null));
        assertEquals(map.containsKey(null),hashmap.containsKey(null));
        assertEquals(map.remove(null),hashmap.remove(null));
        assertEquals(map.containsKey(null),hashmap.containsKey(null));
    }

    @Test
    public void containsValue(){
        assertEquals(map.containsValue(i1),hashmap.containsValue(i1));
        assertEquals(map.put(null,null),hashmap.put(null,null));
        assertEquals(map.containsValue(null),hashmap.containsValue(null));
        assertEquals(map.remove(null),hashmap.remove(null));
        assertEquals(map.containsValue(null),hashmap.containsValue(null));
        assertEquals(map.put("qwe",10),hashmap.put("qwe",10));
        assertEquals(map.put("asd",123456),hashmap.put("asd",123456));
        assertEquals(map.containsValue(10),hashmap.containsValue(10));
        assertEquals(map.containsValue(45),hashmap.containsValue(45));
    }

    @Test
    public void clear(){
        map.clear();
        hashmap.clear();
        assertEquals(map.size(),hashmap.size());
    }

    @Test
    public void isEmpty(){
        assertEquals(map.isEmpty(),hashmap.isEmpty());
        map.clear();
        hashmap.clear();
        assertEquals(map.isEmpty(),hashmap.isEmpty());
        assertEquals(map.put("qwe",10),hashmap.put("qwe",10));
        assertEquals(map.isEmpty(),hashmap.isEmpty());
    }

    @Test
    public void putAll(){
        final Map m = new HashMap();
        new HashMap(){{
            m.put("qw",23);
            m.put("as",45);
            m.put("zx",34);
        }};
        map.putAll(m);
        hashmap.putAll(m);
        assertEquals(map.size(),hashmap.size());
        assertEquals(map.containsValue(10),hashmap.containsValue(10));
        assertEquals(map.containsValue(45),hashmap.containsValue(45));
        assertEquals(map.size(),hashmap.size());
    }

    @Test
    public void keySet(){
        assertEquals(map.put(null,null),hashmap.put(null,null));
        assertEquals(map.put("null",null),hashmap.put("null",null));
        assertEquals(map.put(null,78),hashmap.put(null,78));
        assertEquals(map.put("qwe",10),hashmap.put("qwe",10));
        assertEquals(map.put("asd",123456),hashmap.put("asd",123456));
        assertEquals(map.keySet(),hashmap.keySet());
    }

    @Test
    public<T> void values(){
        assertEquals(map.put(null,null),hashmap.put(null,null));
        assertEquals(map.put("null",null),hashmap.put("null",null));
        assertEquals(map.put(null,78),hashmap.put(null,78));
        assertEquals(map.put("qwe",10),hashmap.put("qwe",10));
        assertEquals(map.put("asd",123456),hashmap.put("asd",123456));
        assertEquals(map.put("qw",10),hashmap.put("qw",10));
        assertTrue(map.values().containsAll(hashmap.values()));
        assertTrue(hashmap.values().containsAll(map.values()));
    }

    @Test
    public void entrySet(){
        assertEquals(map.put(null,null),hashmap.put(null,null));
        assertEquals(map.put("poi",null),hashmap.put("poi",null));
        assertEquals(map.put(null,78),hashmap.put(null,78));
        assertEquals(map.put("qwe",10),hashmap.put("qwe",10));
        assertEquals(map.put("asd",123456),hashmap.put("asd",123456));
        assertEquals(map.put("qw",10),hashmap.put("qw",10));
        assertEquals(map.entrySet(),hashmap.entrySet());
    }

    @Test
    public void equalsAndHash(){
        Map map2 = new MyMap();
        map2.put(s1,i1);
        map2.put(s2,i2);
        map2.put(s3,i3);
        map2.put(s4,i4);
        Map map3 = new MyMap();
        map3.put(s1,i1);
        map3.put(s2,i2);
        map3.put(s3,i3);
        map3.put(s4,i4);
        assertEquals(map.hashCode(),map3.hashCode());
        assertTrue(map.equals(map2));
        assertTrue(map2.equals(map));
    }

}