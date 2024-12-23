package com.company;

import java.util.*;

public class Main {
    public static <T,K> void printMapEntries(Map<T, K> map) {
        Set<Map.Entry<T, K>> entrySet = map.entrySet();
        for (Map.Entry<T, K> entry : entrySet) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        // ---------------- List ----------------
        System.out.println("-".repeat(30));

        List<String> list1 = new ArrayList<>();
        String[] arrStr = { "Daniil", "Ksenia", "Vishnu" };
        Collections.addAll(list1, arrStr);
        System.out.println("list1: " + list1);

        List<String> list2 = new ArrayList<>(list1);
        System.out.println("list2: " + list2);

        List<String> list3 = Arrays.asList("Apple", "Banana", "Cherry");
        System.out.println("list3: " + list3);

        int middleIndex = list2.size() / 2;
        list2.addAll(middleIndex, list3);
        System.out.println("list2: " + list2);

        list2.sort(Collections.reverseOrder());
        System.out.println("list2: " + list2);

        ListIterator<String> iterator = list2.listIterator();
        int ind = 0;
        while (iterator.hasNext()) {
            iterator.next();
            if (ind % 2 != 0) {
                iterator.remove();
            }
            ind++;
        }
        System.out.println("list2: " + list2);


        // ---------------- Set ----------------
        System.out.println("-".repeat(30));

        Set<String> set1 = new HashSet<>();
        set1.add("Dan");
        set1.add("Sob");
        set1.addAll(list1);
        set1.addAll(list2);
        System.out.println("set1: " + set1);

        Set<String> set2 = new LinkedHashSet<>();
        set2.addAll(list2);
        set2.addAll(list3);
        System.out.println("set2: " + set2);


        // ---------------- Map ----------------
        System.out.println("-".repeat(30));

        Map<Integer, String> map1 = new LinkedHashMap<>();
        String[] months = {
                "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
        };
        for (int i = 0; i < months.length; i++) {
            map1.put(i, months[i]);
        }
        System.out.println("map1: " + map1);

        System.out.println("First: " + map1.get(0));
        System.out.println("Last: " + map1.get(map1.size() - 1));

        map1.put(5, "ОТПУСК");
        System.out.println("map1: " + map1);

        Map<Integer, String> map2 = new HashMap<>();
        map2.putAll(map1);
        System.out.println("map2: " + map2);

        printMapEntries(map1);
        System.out.println();
        printMapEntries(map2);

        Map<String, Set<String>> map3 = new HashMap<>();
        map3.put("Daniil", set1);
        map3.put("Bob", set2);
        System.out.println("map3: " + map3);


        // ---------------- User ----------------
        System.out.println("-".repeat(30));

        Set<User> userSet = new HashSet<>();
        User user1 = new User("Daniil", 20, "+380688774414");
        User user2 = new User("Daniil", 20, "+380688774414");
        User user3 = new User("Daniil", 18, "+380688774414");

        userSet.add(user1);
        userSet.add(user2);
        userSet.add(user3);

        System.out.println("userSet: " + userSet);
    }
}