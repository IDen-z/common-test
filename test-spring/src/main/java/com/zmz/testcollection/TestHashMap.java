package com.zmz.testcollection;

import java.util.HashMap;
import java.util.TreeMap;

public class TestHashMap {

    private static HashMap<String, String> hashMap;

    private TreeMap<String, String> treeMap;

    public TestHashMap() {
        hashMap = new HashMap<>(16);
    }

    public static void main(String[] args) {

        hashMap.put("key1", "value1");
        // put的第一步
        // return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);


        Integer.highestOneBit(17);


    }
}
