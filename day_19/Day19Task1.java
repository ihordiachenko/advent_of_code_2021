import java.io.*;
import java.util.*;

public class Day19Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_19/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var codec = br.readLine();
            br.readLine();

            List<List<Character>> input = new ArrayList<List<Character>>();
            String line;
            while ((line = br.readLine()) != null) {
                var row = new ArrayList<Character>();
                for (var ch: line.toCharArray()) {
                    row.add(ch);
                }
                input.add(row);
            }
            var image = new Image(input, codec);
            var enhanced = image.enhance().enhance();

            System.out.println(enhanced.countLitPixels());

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}