import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day5 {
    public void run() throws IOException {
        System.out.println("Answer to question 1: " + part1());
        System.out.println("Answer to question 2: " + part2());
    }

    private String part1() throws IOException {
        var labels = findLabels();
        var mapping = constructMapping(labels);
        applyMoves(mapping);
        return String.join(",", getTopOfContainers(mapping));
    }

    private String part2() throws IOException {
        var labels = findLabels();
        var mapping = constructMapping(labels);
        applyMoves2(mapping);
        return String.join(",", getTopOfContainers(mapping));
    }

    private Map<String, Integer> findLabels() throws IOException {
        Path path = Paths.get("src/resources/day-5.txt");

        return Files.readAllLines(path).stream().filter(line -> {
            var a = line.replace(" ", "");
            var labelLine = a.matches("[0-9]+");
            return labelLine;
        }).map(line -> {
            Map<String, Integer> result = new HashMap<String, Integer>();

            var labels = Stream.of(line.split(" ")).filter(l -> !l.isEmpty()).collect(Collectors.toList());
            int lastIndex = 0;
            for(int i = 0; i < labels.size(); i++) {
                var k = line.indexOf(labels.get(i), lastIndex);
                lastIndex = k;
                result.put(labels.get(i), k);
            }
            return result;
        }).findFirst().get();
    }

    private List<String> getTopOfContainers(Map<String, LinkedList<String>> containers) {
        return containers.values().stream().map(LinkedList::getLast
        ).collect(Collectors.toList());
    }

    private Map<String, LinkedList<String>> constructMapping(Map<String, Integer> labels) throws IOException {
        Path path = Paths.get("src/resources/day-5.txt");
        Map<String, LinkedList<String>> result = new HashMap<>();

        for (String label : labels.keySet()) {
            result.put(label, new LinkedList<>());
        }

        Files.readAllLines(path).stream().filter(line ->
            line.contains("[")
        ).forEach(line -> {
            for (Map.Entry<String, Integer> entry : labels.entrySet()) {
                if(line.length() > entry.getValue()) {
                    var char1 = String.valueOf(line.charAt(entry.getValue()));
                    if (!char1.isBlank()) {
                        var k = result.get(entry.getKey());
                        k.add(0, char1);
                    }
                }
            }
        });
        return result;
    }

    private Map<String, List<String>> applyMoves(Map<String, LinkedList<String>> containers) throws IOException {
        Path path = Paths.get("src/resources/day-5.txt");
        Map<String, List<String>> result = new HashMap<>();

        Pattern p1 = Pattern.compile("^move (.*?) from");
        Pattern p2 = Pattern.compile("from (.*?) to");
        Pattern p3 = Pattern.compile("to (.*?)$");

        Files.readAllLines(path).stream().filter(line ->
            line.contains("move")
        ).forEach(line -> {
            Matcher m1 = p1.matcher(line);
            Matcher m2 = p2.matcher(line);
            Matcher m3 = p3.matcher(line);

            int amount = 0;
            int source = 0;
            int target = 0;
            while(m1.find()) {
                amount = Integer.parseInt(m1.group(1));
            }
            while(m2.find()) {
                source = Integer.parseInt(m2.group(1));
            }
            while(m3.find()) {
                target = Integer.parseInt(m3.group(1));
            }

            var from = containers.get(String.valueOf(source));
            var to = containers.get(String.valueOf(target));

            for(int i = 0; i < amount; i++) {
                var moved = from.get(from.size() - 1);
                from.remove(from.size() - 1);
                to.add(moved);
            }
        });
        return result;
    }

    private Map<String, List<String>> applyMoves2(Map<String, LinkedList<String>> containers) throws IOException {
        Path path = Paths.get("src/resources/day-5.txt");
        Map<String, List<String>> result = new HashMap<>();

        Pattern p1 = Pattern.compile("^move (.*?) from");
        Pattern p2 = Pattern.compile("from (.*?) to");
        Pattern p3 = Pattern.compile("to (.*?)$");

        Files.readAllLines(path).stream().filter(line ->
                line.contains("move")
        ).forEach(line -> {
            Matcher m1 = p1.matcher(line);
            Matcher m2 = p2.matcher(line);
            Matcher m3 = p3.matcher(line);

            int amount = 0;
            int source = 0;
            int target = 0;
            while(m1.find()) {
                amount = Integer.parseInt(m1.group(1));
            }
            while(m2.find()) {
                source = Integer.parseInt(m2.group(1));
            }
            while(m3.find()) {
                target = Integer.parseInt(m3.group(1));
            }

            var from = containers.get(String.valueOf(source));
            var to = containers.get(String.valueOf(target));

            var toBeMoves = new LinkedList<String>();

            for(int i = 0; i < amount; i++) {
                var moved = from.get(from.size() - 1);
                from.remove(from.size() - 1);
                toBeMoves.add(moved);
            }
            var tempSize = toBeMoves.size();
            for(int i = 0; i < tempSize; i++) {
                var moved = toBeMoves.get(toBeMoves.size() - 1);
                toBeMoves.remove(toBeMoves.size() - 1);
                to.add(moved);
            }
        });
        return result;
    }
}
