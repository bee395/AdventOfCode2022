import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day6 {
    public void run() throws IOException {
        System.out.println("Answer to question 1: " + part1());
        System.out.println("Answer to question 2: " + part2());
    }

    private int part1() throws IOException {
        Path path = Paths.get("src/resources/day-6.txt");

        Queue<Character> lastChars = new LinkedList<>();
        var count = 4;

        var chars = Files.readAllLines(path).get(0).toCharArray();

        for(int i = 0; i < chars.length; i++) {
            var a = chars[i];
            lastChars.add(a);
            if(lastChars.size() == count) {
                if(lastChars.stream().distinct().count() == lastChars.size()) {
//                    System.out.println(lastChars);
                    return i + 1;
                } else {
                    lastChars.remove();
                }
            }
        }
        return -1;
    }

    private int part2() throws IOException {
        Path path = Paths.get("src/resources/day-6.txt");

        Queue<Character> lastChars = new LinkedList<>();
        var count = 14;

        var chars = Files.readAllLines(path).get(0).toCharArray();

        for(int i = 0; i < chars.length; i++) {
            var a = chars[i];
            lastChars.add(a);
            if(lastChars.size() == count) {
                if(lastChars.stream().distinct().count() == lastChars.size()) {
//                    System.out.println(lastChars);
                    return i + 1;
                } else {
                    lastChars.remove();
                }
            }
        }
        return -1;
    }
}
