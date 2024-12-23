package org.brainacad;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class TestMaven {
    public static void main(String[] args) {
        List<String> fruits = Lists.newArrayList("orange", "banana", "kiwi");
        System.out.println(fruits);

        List<String> reverseFruits = Lists.reverse(fruits);
        reverseFruits.forEach(System.out::println);

        Multimap<String, String> map = ArrayListMultimap.create();
        map.put("abc123", "Daniil");
        map.put("def456", "Thomas");
        System.out.println(map);

        // -----------------------------
        System.out.println("-".repeat(20));

        Properties prop = new Properties();
        try (InputStream resourceAsStream = TestMaven.class.getClassLoader()  // лоадер текущего класса
                .getResourceAsStream("config.properties")) {  // получение файла настроек
            if (resourceAsStream != null) {
                prop.load(resourceAsStream);  // загрузка настроек из файла
            }
            else {
                System.out.println("NULL");
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(prop.get("props.local.hello"));
        System.out.println(prop.get("props.mvn.hello"));
    }
}
