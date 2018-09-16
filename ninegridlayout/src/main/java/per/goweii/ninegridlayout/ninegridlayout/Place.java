package per.goweii.ninegridlayout.ninegridlayout;

/**
 * 描述：
 *
 * @author Cuizhen
 * @date 2018/9/12
 */
public class Place {

    private int left;
    private int top;
    private int right;
    private int bottom;

    public Place(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }
}
