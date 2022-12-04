import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day4 {
    public void run() throws IOException {
        System.out.println("Answer to question 1: " + part1());
        System.out.println("Answer to question 2: " + part2());
    }
//
    private int part1() throws IOException {
        Path path = Paths.get("src/resources/day-4.txt");

        return (int)Files.readAllLines(path).stream().filter(line -> {
            String[] section = line.split(",");

            var full1 = getFullSection(section[0]);
            var full2 = getFullSection(section[1]);

            return full1.containsAll(full2) || full2.containsAll(full1);
        }).count();
    }

    private int part2() throws IOException {
        Path path = Paths.get("src/resources/day-4.txt");

        return (int)Files.readAllLines(path).stream().filter(line -> {
            String[] section = line.split(",");

            var full1 = getFullSection(section[0]);
            var full2 = getFullSection(section[1]);

            return full1.stream().anyMatch(full2::contains);
        }).count();
    }

    private List<Integer> getFullSection(String section) {
        String[] edgeNumbers = section.split("-");
        Integer start = Integer.valueOf(edgeNumbers[0]);
        Integer end = Integer.valueOf(edgeNumbers[1]);
        var result = new ArrayList<Integer>();
        for(int i = start; i <= end; i++) {
            result.add(i);
        }
        return result;
    }
}
