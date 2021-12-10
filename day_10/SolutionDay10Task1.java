import java.io.*;
import java.util.*;

public class SolutionDay10Task1 {
    public static void main(String[] args) {
        try {
            Map<Character, Integer> scoresByBracket = Map.of(
                ')', 3,
                ']', 57,
                '}', 1197,
                '>', 25137
            );
            Map<Character, Character> brackets = Map.of(
                ')', '(',
                ']', '[',
                '}', '{',
                '>', '<'
            );

            var fs = new FileInputStream("./day_10/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            int errorScore = 0;
            String line;
            while ((line = br.readLine()) != null) {
                var stack = new Stack<Character>();
                for (var ch: line.toCharArray()) {
                    if (brackets.containsKey(ch)) {
                        if (stack.isEmpty() || stack.peek() != brackets.get(ch)) {
                            errorScore += scoresByBracket.get(ch);
                            break;
                        } else {
                            stack.pop();
                        }
                    } else {
                        stack.push(ch);
                    }
                }
            }

            System.out.println(errorScore);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}