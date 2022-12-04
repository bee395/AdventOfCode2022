import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day2 {
    Map<String, Integer> map = new HashMap<>();

    public Day2() {
        map.put("A", 1);
        map.put("X", 1);
        map.put("B", 2);
        map.put("Y", 2);
        map.put("C", 3);
        map.put("Z", 3);
    }

    public void run() throws IOException {
        System.out.println("Answer to question 1: " + part1());
        System.out.println("Answer to question 2: " + part2());
    }

    private int part1() throws IOException {
        Path path = Paths.get("src/resources/day-2.txt");

        return Files.readAllLines(path).stream().map(line -> {
            var player1 = line.split(" ")[1];
            var player2 = line.split(" ")[0];

            var won = Objects.equals(map.get(player1), map.get(player2) + 1) || (map.get(player1) == 1 && map.get(player2) == 3);
            var draw = Objects.equals(map.get(player1), map.get(player2));
            var score = (won ? 6 : draw ? 3 : 0) + map.get(player1);
            return score;
        }).reduce(0, Integer::sum);
    }

    private int part2() throws IOException {
        Path path = Paths.get("src/resources/day-2.txt");

        return Files.readAllLines(path).stream().map(line -> {
            var player1 = line.split(" ")[1];
            var player2 = line.split(" ")[0];
            var moveToPlay = 0;

            var play1 = map.get(player1) -1;
            var play2 = map.get(player2) -1;
            switch (play1) {
                case 0:
                    moveToPlay = (play2 + 3 - 1)%3 + 1;
                    break;
                case 1:
                    moveToPlay = (play2 + 3 - 0)%3 + 1;
                    break;
                case 2:
                    moveToPlay = (play2 + 3 + 1)%3 + 1;
                    break;
            }
            var won = Objects.equals(moveToPlay, map.get(player2) + 1) || (moveToPlay == 1 && map.get(player2) == 3);
            var draw = Objects.equals(moveToPlay, map.get(player2));

            var score = (won ? 6 : draw ? 3 : 0) + moveToPlay;
            return score;
        }).reduce(0, Integer::sum);
    }
}
