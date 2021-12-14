import java.io.*;
import java.util.*;

public class Day14Task1 {
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

            for (int iteration = 0; iteration < 10; iteration++) {
                var nextState = new StringBuilder();
                nextState.append(state.charAt(0));

                for (int i = 1; i < state.length(); i++) {
                    String key = state.charAt(i-1) + "" + state.charAt(i);
                    if (insertionRules.containsKey(key)) {
                        nextState.append(insertionRules.get(key));
                    }
                    nextState.append(state.charAt(i));
                }
                state = nextState.toString();
            }

            var map = new HashMap<Character, Integer>();
            for (var ch: state.toCharArray()) {
                map.put(ch, map.getOrDefault(ch, 0)+1);
            }

            int result = Collections.max(map.values()) - Collections.min(map.values());
            System.out.println(result);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}