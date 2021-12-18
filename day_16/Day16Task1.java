import java.io.*;

public class Day16Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_16/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var packet = new Packet(br.readLine());
            System.out.println(sumVersions(packet, 0));

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // recursively parse versions
    static int sumVersions(Packet packet, int start) {
        if (start >= packet.length()) {
            return 0;
        }

        int version = packet.read(start, 3);
        int typeId = packet.read(start+3, 3);

        int result = version, pointer = start+6;;

        if (typeId == 4) {
            while (packet.at(pointer)) {
                pointer += 5;
            }
            pointer += 5;
            result += sumVersions(packet, pointer);
        } else if (packet.at(pointer)) {
            // number of subpackets (11 bit)
            result += sumVersions(packet, pointer + 1 + 11);
        } else {
            // size of subpackets (15 bit)
            result += sumVersions(packet, pointer + 1 + 15);
        }

        return result;
    }
}