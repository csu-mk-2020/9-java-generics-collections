package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Task03Main {

    public static void main(String[] args) throws FileNotFoundException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
        HashMap<Integer, Set<String>> wordMap = new HashMap<>();
        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                if (line.length() < 3) continue;

                line = line.toLowerCase();
                if (line.chars().allMatch(s -> s >= 'а' && s <= 'я')) {
                    Set<String> set = wordMap.computeIfAbsent(line.length(), key -> new HashSet<>());
                    set.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        HashSet<Set<String>> anagrams = new HashSet<>();
        for (Set<String> set : wordMap.values()) {
            for (String s : set) {
                TreeSet<String> tmp = new TreeSet<>();
                int[] chars = s.chars().sorted().toArray();
                for (String s2 : set) {
                    int[] chars2 = s.chars().sorted().toArray();
                    if (Arrays.equals(chars, chars2)){
                        tmp.add(s2);
                    }
                }
                if (tmp.size() > 1) {
                    anagrams.add(tmp);
                }
            }
        }

        for (Set<String> set : anagrams) {
            for (String s : set) {
                System.out.println(s);
            }
        }

        ArrayList<Set<String>> result = new ArrayList<>(anagrams);
        result.sort(Comparator.comparing(s -> s.iterator().next()));
        return result;
    }
}
