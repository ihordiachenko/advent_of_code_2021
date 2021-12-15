import java.io.*;
import java.util.*;

public class Day14Task2 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_14/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var state = br.readLine();
            br.readLine();

            var insertionRules = new HashMap<String, String>();
            String line;
            while ((line = br.readLine()) != null) {
                var pair = line.split(" -> ");
                insertionRules.put(pair[0], pair[1]);
            }

            /** Merge rule after insertion:
             *   AB
             * AC  CB - middle char is duplicated, need to dec it's count after merge
             */
            int days = 40;
            var memo = new HashMap<String, Map<String, Long>>();
            Map<String, Long> charCounts = new HashMap<String, Long>();
            for (int i = 1; i < state.length(); i++) {
                String duplet = state.charAt(i - 1) + "" + state.charAt(i);
                charCounts = merge(charCounts, dp(duplet, days, insertionRules, memo));
                if (i > 1) {
                    String ch = state.charAt(i - 1)+"";
                    charCounts.put(ch, charCounts.get(ch)-1);
                }
            }

            long result = Collections.max(charCounts.values()) - Collections.min(charCounts.values());
            System.out.println(result);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static Map<String, Long> dp(
        String duplet,
        int daysLeft,
        Map<String, String> insertionRules,
        Map<String, Map<String, Long>> memo
    ) {
        // System.out.format("day: %d\n", daysLeft);
        var key = String.format("%s%d", duplet, daysLeft);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        if (daysLeft == 0 || !insertionRules.containsKey(duplet)) {
            var result = new HashMap<String, Long>();
            String ch1 = duplet.charAt(0)+"", ch2 = duplet.charAt(1)+"";
            result.put(ch1, 1L);
            result.put(ch2, result.getOrDefault(ch2, 0L) + 1L);
            memo.put(key, result);

            return result;
        }

        String middleChar = insertionRules.get(duplet);
        var left = dp(duplet.charAt(0)+middleChar, daysLeft-1, insertionRules, memo);
        var right = dp(middleChar+duplet.charAt(1), daysLeft-1, insertionRules, memo);
        var result = merge(new HashMap<String, Long>(left), right);
        result.put(middleChar, result.get(middleChar)-1); // dedup the middle char

        memo.put(key, result);

        return result;
    }

    static Map<String, Long> merge(Map<String, Long> map1, Map<String, Long> map2) {
        for (var ch : map2.keySet()) {
            map1.put(ch, map1.getOrDefault(ch, 0L) + map2.get(ch));
        }

        return map1;
    }
}