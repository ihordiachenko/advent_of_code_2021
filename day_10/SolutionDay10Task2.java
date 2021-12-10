import java.io.*;
import java.util.*;

public class SolutionDay10Task2 {
    public static void main(String[] args) {
        try {
            Map<Character, Long> scoresByBracket = Map.of(
                '(', 1L,
                '[', 2L,
                '{', 3L,
                '<', 4L
            );
            Map<Character, Character> brackets = Map.of(
                ')', '(',
                ']', '[',
                '}', '{',
                '>', '<'
            );

            var fs = new FileInputStream("./day_10/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var scores = new ArrayList<Long>();
            String line;
main:       while ((line = br.readLine()) != null) {
                var stack = new Stack<Character>();
                for (var ch : line.toCharArray()) {
                    if (brackets.containsKey(ch)) {
                        if (stack.isEmpty() || stack.peek() != brackets.get(ch)) {
                            continue main;
                        }

                        stack.pop();
                    } else {
                        stack.push(ch);
                    }
                }

                long score = 0;
                while (!stack.isEmpty()) {
                    score = 5*score + scoresByBracket.get(stack.pop());
                }
                scores.add(score);
            }

            scores.sort(Collections.reverseOrder());
            long middleScore = scores.get(scores.size()/2);

            System.out.println(middleScore);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}