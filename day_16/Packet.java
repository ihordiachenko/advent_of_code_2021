import java.util.*;

public class Packet {
    private BitSet bits;
    private int length = 0;

    public Packet(String hexString) {
        this.length = hexString.length()*4;
        this.bits = new BitSet();

        int start = 0;
        for (char ch: hexString.toCharArray()) {
            int halfByte = Integer.parseInt(ch+"", 16);
            bits.set(start, (halfByte&0b1000) > 0);
            bits.set(start+1, (halfByte & 0b100) > 0);
            bits.set(start+2, (halfByte & 0b10) > 0);
            bits.set(start+3, (halfByte & 0b1) > 0);

            start += 4;
        }
    }

    public int length() {
        return this.length;
    }

    public boolean at(int i) {
        return this.bits.get(i);
    }

    public int read(int start, int size) {
        int result = 0;
        for (int i = 0; i < size; i++) {
            result |= (at(start+i) ? 1 : 0) << size-i-1;
        }

        return result;
    }

    public String toBinary() {
        var sb = new StringBuilder();
        for (int i = 0; i < length(); i++) {
            sb.append(at(i)?1 : 0);
        }

        return sb.toString();
    }
}
