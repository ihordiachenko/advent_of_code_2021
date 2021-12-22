import java.util.*;

public class Image {
    List<List<Character>> pixels;
    String codec;
    char backgroundPixel = '.';

    static final char LIT = '#';

    public Image(List<List<Character>> pixels, String codec) {
        this.pixels = pixels;
        this.codec = codec;
    }

    public Image(List<List<Character>> pixels, String codec, char backgroundPixel) {
        this(pixels, codec);
        this.backgroundPixel = backgroundPixel;
    }

    char enhancePixel(int pixelI, int pixelJ) {
        int codecIndex = 0;
        for (int i = pixelI-1; i <= pixelI+1; i++) {
            for (int j = pixelJ-1; j <= pixelJ+1; j++) {
                codecIndex = (codecIndex<<1) | (isLit(i, j) ? 1 : 0);
            }
        }

        return codec.charAt(codecIndex);
    }

    boolean isLit(int i, int j) {
        if (i < 0 || i >= pixels.size() || j < 0 || j >= pixels.size()) {
            return backgroundPixel == LIT;
        }

        return pixels.get(i).get(j) == LIT;
    }

    public Image enhance() {
        // TODO: handle infinity
        var resultPixels = new ArrayList<List<Character>>();
        for (int i = -3; i < pixels.size()+3; i++) {
            var row = new ArrayList<Character>();
            for (int j = -3; j < pixels.get(0).size()+3; j++) {
                row.add(enhancePixel(i, j));
            }
            resultPixels.add(row);
        }

        return new Image(
            resultPixels,
            codec,
            backgroundPixel == LIT ? codec.charAt(511) : codec.charAt(0)
        );
    }

    public int countLitPixels() {
        int result = 0;
        for (List<Character> row: pixels) {
            for (var pixel: row) {
                result += pixel == LIT ? 1 : 0;
            }
        }

        return result;
    }

    public void print() {
        for (var row: pixels) {
            for (var ch: row) {
                System.out.print(ch);
            }
            System.out.println();
        }
        System.out.println();
    }
}