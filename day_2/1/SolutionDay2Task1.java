import java.io.*;

public class SolutionDay2Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_2/1/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            int depth = 0, horizontalPosition = 0, aim = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineArgs = line.split(" ");
                String directrion = lineArgs[0];
                int delta = Integer.parseInt(lineArgs[1]);

                if (directrion.equals("up")) {
                    aim -= delta;
                } else if (directrion.equals("down")) {
                    aim += delta;
                } else {
                    horizontalPosition += delta;
                    depth += aim*delta;
                }
            }

            System.out.println(depth*horizontalPosition);
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}