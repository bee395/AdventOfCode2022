import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day3 {
    Map<Character, Integer> map = new HashMap<>();
    int counter = 0;
    int totalScore = 0;

    public void run() throws IOException {
        System.out.println("Answer to question 1: " + part1());
        System.out.println("Answer to question 2: " + part2());
    }
//
    private int part1() throws IOException {
        Path path = Paths.get("src/resources/day-3.txt");
        return Files.readAllLines(path).stream().map(line -> {
            var compartments = getCompartments(line);
            var duplicate = getDuplicates(compartments);
            var score = getScoreFromItem(duplicate.get());
            return score;
        }).reduce(0, Integer::sum);
    }

    private int part2() throws IOException {
        Path path = Paths.get("src/resources/day-3.txt");

        Files.readAllLines(path).forEach(line -> {
            line.chars().distinct().mapToObj(c -> (char) c).forEach(a -> {
                var currentValue = map.getOrDefault(a, 0);
                map.put(a, ++currentValue);
            });
            counter++;
            if(counter==3) {
                var uniqueChar = findUniqueBatch();
                totalScore += getScoreFromItem(uniqueChar);
                map = new HashMap<>();
                counter = 0;
            }
        });
        return totalScore;
    }

    private Character findUniqueBatch() {
        return map.entrySet().stream().filter(a -> a.getValue() == 3).map(Map.Entry::getKey).findFirst().get();
    }

    private List<String> getCompartments(String content) {
        final int mid = content.length() / 2;
        return  List.of(content.substring(0, mid),content.substring(mid));
    }

    private Optional<Character> getDuplicates(List<String> compartments) {
        var items = compartments.get(0).chars().mapToObj(c -> (char) c).collect(Collectors.toSet());

        return compartments.get(1).chars().mapToObj(c -> (char) c).filter(items::contains).findFirst();
    }

    private int getScoreFromItem(Character character) {
        if(Character.isUpperCase(character)) {
            return  (int) character - (int) 'A' + 27;
        } else {
            return  (int) character - (int) 'a' + 1;
        }
    }
}
