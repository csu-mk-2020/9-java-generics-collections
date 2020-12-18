package com.example.task03;

import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import java.util.*;
import java.util.stream.Stream;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
        Map<Integer, Set<String>> map = new HashMap<>();
        bufferedReader.lines().filter(line -> line.length() > 3).forEach(line -> {
            Stream<Character.UnicodeBlock> unicodeBlockStream = line.chars().mapToObj(Character.UnicodeBlock::of);
            if (unicodeBlockStream.allMatch(str -> str.equals(Character.UnicodeBlock.CYRILLIC))) {
                Set<String> set = map.computeIfAbsent(line.length(), key -> new HashSet<>());
                set.add(line.toLowerCase());
            }
        });

        Set<Set<String>> result = new HashSet<>();
        map.forEach((key, set) -> set.forEach(str -> {
            Set<String> tmp = new TreeSet<>();
            int[] symbols = str.chars().sorted().toArray();
            set.forEach(another -> {
                if (Arrays.equals(symbols, another.chars().sorted().toArray())) {
                    tmp.add(another);
                }
            });
            if (tmp.size() > 1) {
                result.add(tmp);
            }
        }));
        List<Set<String>> sortResult = new ArrayList<>(result);
        sortResult.sort(Comparator.comparing(str -> str.iterator().next()));
        return sortResult;
    }
}
