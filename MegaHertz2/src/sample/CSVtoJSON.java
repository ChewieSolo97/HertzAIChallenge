package sample;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVtoJSON {
    String json;

    public static String readJSon() {
        StringBuilder input = new StringBuilder();
        try {

            Files.lines(Paths.get("dataTest.txt"), StandardCharsets.UTF_8).forEach(s -> input.append(s + " "));
        } catch (Exception e) {

        }
        return input.toString();
    }
}
