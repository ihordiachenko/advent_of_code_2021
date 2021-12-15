import java.io.*;

public class Day8Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_08/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            int result = 0;
            String line;
            while ((line = br.readLine()) != null) {

                String output = line.split("\\|")[1];
                for (var code: output.split(" ")) {
                    int n = code.trim().length();
                    result += n > 0 && (n < 5 || code.length() == 7) ? 1 : 0;
                }
            }

            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}