import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Day1 {
    int counter = 0;

    public void run() throws IOException {
        var top3 = getResult().limit(3).reduce(0, Integer::sum);

        System.out.println("Answer to question 1: {}" + getResult().findFirst().get());
        System.out.println("Answer to question 2: {}" + top3);
    }

    private Stream<Integer> getResult() throws IOException {
        Path path = Paths.get("src/resources/day-1.txt");

        Map<Integer, Set<Integer>> map = new HashMap<>();

        Files.readAllLines(path).forEach(a -> {
            if(a.isEmpty()) {
                counter++;
            } else {
                Integer value = Integer.valueOf(a);
                map.putIfAbsent(counter, new HashSet<>());
                map.computeIfPresent(counter, (b, c) -> {
                    c.add(value);
                    return c;
                });
            }
        });

        return map.values().stream().map(a -> a.stream().mapToInt(Integer::intValue).sum()).sorted(Collections.reverseOrder());
    }
}
