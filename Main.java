import java.util.*;
import java.util.stream.*;
import java.nio.file.*;
import java.io.*;
import java.util.function.Function;

class Utils {
    public static OptionalDouble extnd(String str) {
        try {
            return OptionalDouble.of(Double.parseDouble(str));
        }
        catch (NumberFormatException e) {
            return OptionalDouble.empty();
        }
    }
}

public class Main {
    // posted some code on live session
    public static String getMax(String line) {
            OptionalDouble max = Arrays.stream(line.split("\\s+"))
            .map(str -> Utils.extnd(str))
            .filter(x -> x.isPresent())
            .mapToDouble(OptionalDouble::getAsDouble)
            .max();
            return max.isPresent() ? Double.toString(max.getAsDouble())
           : "(No Valid Numbers on Line)";
    }
    public static void main(String[] args) {
        try (Stream<String> lines = Files.lines(Paths.get(args[0]))) {
            Stream<String> maxes = lines.map(str -> getMax(str));
            Files.write(Paths.get(args[1]),
                (Iterable<String>)maxes::iterator);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
