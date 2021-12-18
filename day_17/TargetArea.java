public class TargetArea {
    int left, right, top, bottom;

    public TargetArea(int x1, int x2, int y1, int y2) {
        this.left = Math.min(x1, x2);
        this.right = Math.max(x1, x2);
        this.top = Math.max(y1, y2);
        this.bottom = Math.min(y1, y2);
    }

    public boolean contains(int x, int y) {
        return (x >= left && x <= right) && (y >= bottom && y <= top);
    }
}
