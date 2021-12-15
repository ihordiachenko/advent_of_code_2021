import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day8Task2 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_08/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            int result = 0;
            String line;
            while ((line = br.readLine()) != null) {
                var parts = line.split("\\|");
                int num = decodeNumber(parseCodes(parts[0]), parseCodes(parts[1]));
                System.out.println(num);
                result += num;
            }

            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static List<String> parseCodes(String rawCodes) {
        var result = new ArrayList<String>();
        for (var code : rawCodes.split(" ")) {
            code = code.trim();
            if (code.length() > 0) {
                result.add(code);
            }
        }

        return result;
    }

    static int decodeNumber(List<String> patterns, List<String> outputs) {
        var codeToDigit = new HashMap<String, Integer>();
        patterns.sort((a, b) -> a.length() - b.length());

        // Guess digits with unique code length
        codeToDigit.put(normalize(patterns.get(0)), 1);
        codeToDigit.put(normalize(patterns.get(1)), 7);
        codeToDigit.put(normalize(patterns.get(2)), 4);
        codeToDigit.put(normalize(patterns.get(9)), 8);

        ///// 6 digits:
        // Guess 9: must contains 4
        var pattern4 = stringToSet(patterns.get(2));
        for (int i = 6; i < 9; i++) {
            var code = normalize(patterns.get(i));
            var pattern = stringToSet(code);
            pattern.retainAll(pattern4);
            if (pattern.size() == 4) {
                codeToDigit.put(code, 9);
                // System.out.format("9: %s\n", code);

                break;
            }
        }

        // Guess: 0 contains 1
        var pattern1 = stringToSet(patterns.get(0));
        for (int i = 6; i < 9; i++) {
            var code = normalize(patterns.get(i));
            if (codeToDigit.containsKey(code)) {
                continue;
            }
            var pattern = stringToSet(code);
            pattern.retainAll(pattern1);
            if (pattern.size() == 2) {
                codeToDigit.put(code, 0);
                // System.out.format("0: %s\n", code);
                break;
            }
        }

        // 6 is the rest
        Set<Character> pattern6 =  null;
        for (int i = 6; i < 9; i++) {
            var code = normalize(patterns.get(i));
            if (codeToDigit.containsKey(code)) {
                continue;
            }
            codeToDigit.put(code, 6);
            // System.out.format("6: %s\n", code);
            pattern6 = stringToSet(code);
            break;
        }

        ///// 5 digits:
        // 3 contains 1
        for (int i = 3; i < 6; i++) {
            var code = normalize(patterns.get(i));
            var pattern = stringToSet(code);
            pattern.retainAll(pattern1);
            if (pattern.size() == 2) {
                // System.out.format("3: %s\n", code);
                codeToDigit.put(code, 3);
                break;
            }
        }

        for (int i = 3; i < 6; i++) {
            var code = normalize(patterns.get(i));
            if (codeToDigit.containsKey(code)) {
                continue;
            }
            var pattern = stringToSet(code);
            pattern.retainAll(pattern6);
            if (pattern.size() == 5) {
                codeToDigit.put(code, 5);
                // System.out.format("5: %s\n", code);
                break;
            }
        }

        for (int i = 3; i < 6; i++) {
            var code = normalize(patterns.get(i));
            if (codeToDigit.containsKey(code)) {
                continue;
            }
            // System.out.format("2: %s\n", code);

            codeToDigit.put(code, 2);
            break;
        }
        // System.out.println("2");

        return 1000*codeToDigit.get(normalize(outputs.get(0)))
            + 100*codeToDigit.get(normalize(outputs.get(1)))
            + 10*codeToDigit.get(normalize(outputs.get(2)))
            + codeToDigit.get(normalize(outputs.get(3)));
    }

    static Set<Character> stringToSet(String s) {
        var set = new HashSet<Character>();
        for (var ch: s.toCharArray()) {
            set.add(ch);
        }

        return set;
    }

    static String normalize(String s) {
        // this is embarassing
        var list = new ArrayList<Character>();
        for (var ch: s.toCharArray()) {
            list.add(ch);
        }
        list.sort((a, b) -> a-b);

        var sb = new StringBuilder();
        for (var ch: list) {
            sb.append(ch);
        }

        return sb.toString();
    }

    /**
     * fdgacbe cefdb cefbgd gcbe: 8394
     * fcgedb cgb dgebacf gc: 9781
     * cg cg fdcagb cbg: 1197
     * efabcd cedba gadfec cb: 9361
     * gecf egdcabf bgf bfgea: 4873
     * gebdcfa ecba ca fadegcb: 8418
     * cefg dcbef fcge gbcadfe: 4548
     * ed bcgafe cdgba cbgef: 1625
     * gbdfcae bgc cg cgb: 8717
     * fgae cfgab fg bagce: 4315
     */
}