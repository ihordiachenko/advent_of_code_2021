import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

class ExecResult {
    public long result;
    public int packetSize;

    public ExecResult(long result, int packetSize) {
        this.result = result;
        this.packetSize = packetSize;
    }
}

public class Day16Task2 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_16/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var packet = new Packet(br.readLine());
            System.out.println(exec(packet, 0).result);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static ExecResult exec(Packet packet, int start) {
        // NOTE: ignore version bits for part 2
        int typeId = packet.read(start+3, 3);
        int pointer = start+6;
        long result = 0;

        // System.out.format("typeId: %d, version: %d\n", typeId, version);

        if (typeId == 4) {
            // literal value
            boolean hasMore = true;
            while (hasMore) {
                hasMore = packet.at(pointer);
                pointer++;
                result = (result << 4) | packet.read(pointer, 4);
                pointer += 4;
            }

            return new ExecResult(result, pointer-start);
        }

        var operands = new LinkedList<Long>();
        if (packet.at(pointer)) {
            pointer++;
            int subpacketsLeft = packet.read(pointer, 11);
            pointer += 11;

            while (subpacketsLeft > 0) {
                var res = exec(packet, pointer);
                operands.add(res.result);
                pointer += res.packetSize;
                subpacketsLeft--;
            }

            //System.out.format("num subpackets\n");
        } else {
            pointer++;
            int bitsLeft = packet.read(pointer, 15);
            pointer += 15;

            while (bitsLeft > 0) {
                var res = exec(packet, pointer);
                operands.add(res.result);
                pointer += res.packetSize;
                bitsLeft -= res.packetSize;
            }

            // System.out.format("subpackets size\n");
        }

        switch (typeId) {
            case 0:
                // sum
                result = 0;
                for (long operand: operands) {
                    result += operand;
                }
                break;
            case 1:
                // product
                result = operands.get(0);
                for (int i = 1; i < operands.size(); i++) {
                    result *= operands.get(i);
                }
                break;
            case 2:
                // min
                result = Collections.min(operands);
                break;
            case 3:
                // max
                result = Collections.max(operands);
                break;
            case 5:
                // gt
                result = operands.get(0) > operands.get(1) ? 1 : 0;
                break;
            case 6:
                // lt
                result = operands.get(0) < operands.get(1) ? 1 : 0;
                break;
            case 7:
                // eq
                result = operands.get(0).equals(operands.get(1)) ? 1 : 0;
                break;
        }

        return new ExecResult(result, pointer-start);
    }
}