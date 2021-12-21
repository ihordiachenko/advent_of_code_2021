public class SnailfishNumber {
    public SnailfishNumber left = null;
    public SnailfishNumber right = null;
    public SnailfishNumber parent = null;
    public boolean isRegularNumber = false;
    public Integer value = null;

    public SnailfishNumber(SnailfishNumber parent, SnailfishNumber left, SnailfishNumber right) {
        this.parent = parent;

        this.left = left;
        left.parent = this;

        this.right = right;
        right.parent = this;
    }

    public SnailfishNumber(SnailfishNumber parent, int regularNumber) {
        this.parent = parent;
        this.isRegularNumber = true;
        this.value = regularNumber;
    }

    public SnailfishNumber add(SnailfishNumber num) {
        var result = new SnailfishNumber(null, this, num);
        return result.reduce();
    }

    public SnailfishNumber reduce() {
        boolean reduceMore = true;
        while (reduceMore) {
            if (tryExplode(0) || trySplit()) {
                reduceMore = true;
            } else {
                reduceMore = false;
            }
        }

        return this;
    }

    boolean trySplit() {
        if (isRegularNumber) {
            if (value >= 10) {
                this.split();
                return true;
            }

            return false;
        }

        return left.trySplit() || right.trySplit();
    }

    void split() {
        this.left = new SnailfishNumber(this, (int) Math.floor(this.value / 2.0));
        this.right = new SnailfishNumber(this, (int) Math.ceil(this.value / 2.0));
        this.isRegularNumber = false;
        this.value = null;
    }

    boolean tryExplode(int depth) {
        if (isRegularNumber) {
            return false;
        }
        if (depth == 4) {
            this.explode();
            return true;
        }

        return left.tryExplode(depth+1) || right.tryExplode(depth+1);
    }

    void explode() {
        int leftValue = left.value;
        int rightValue = right.value;

        // Explode the target
        isRegularNumber = true;
        value = 0;
        left = null;
        right = null;

        // Update closest regular numbers
        var leftClosest = findLeftAdjacent();
        if (leftClosest != null) {
            leftClosest.value += leftValue;
        }
        var rightClosest = findRightAdjacent();
        if (rightClosest != null) {
            rightClosest.value += rightValue;
        }
    }

    SnailfishNumber findLeftAdjacent() {
        if (parent == null) {
            return null;
        }

        if (parent.left != this) {
            var leftRes = dfsLeftSubtree(parent.left);
            if (leftRes != null) {
                return leftRes;
            }
        }

        return parent.findLeftAdjacent();
    }

    SnailfishNumber findRightAdjacent() {
        if (parent == null) {
            return null;
        }

        if (parent.right != this) {
            var rightRes = dfsRightSubtree(parent.right);
            if (rightRes != null) {
                return rightRes;
            }
        }

        return parent.findRightAdjacent();
    }

    SnailfishNumber dfsLeftSubtree(SnailfishNumber node) {
        if (node.isRegularNumber) {
            return node;
        }

        var rightRes = dfsLeftSubtree(node.right);
        if (rightRes != null) {
            return rightRes;
        }

        return dfsLeftSubtree(node.left);
    }

    SnailfishNumber dfsRightSubtree(SnailfishNumber node) {
        if (node.isRegularNumber) {
            return node;
        }

        var leftRes = dfsRightSubtree(node.left);
        if (leftRes != null) {
            return leftRes;
        }

        return dfsRightSubtree(node.right);
    }

    public int calcMagnitude() {
        if (isRegularNumber) {
            return value;
        }

        return 3*left.calcMagnitude() + 2*right.calcMagnitude();
    }

    public static SnailfishNumber parse(String s) {
        if (isNumeric(s)) {
            int val = Integer.parseInt(s);
            return new SnailfishNumber(null, val);
        }

        // seek comma after balanced brackets to split into left and right parts
        s = s.substring(1, s.length()-1); // strip outer brackets
        int i = 0, openBrackets = 0;
        for (; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                openBrackets++;
            } else if (s.charAt(i) == ']') {
                openBrackets--;
            } else if (s.charAt(i) == ',' && openBrackets == 0) {
                break;
            }
        }

        return new SnailfishNumber(null, parse(s.substring(0, i)), parse(s.substring(i+1)));
    }

    static boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String toString() {
        if (isRegularNumber) {
            return value.toString();
        }

        return String.format("[%s,%s]", left.toString(), right.toString());
    }

    public SnailfishNumber clone() {
        if (isRegularNumber) {
            return new SnailfishNumber(null, value);
        }

        return new SnailfishNumber(null, left.clone(), right.clone());
    }
}
