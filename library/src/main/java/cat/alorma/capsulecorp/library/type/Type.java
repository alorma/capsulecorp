package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;

/**
 * Created by adria on 6/02/14.
 */
public abstract class Type {
    private Rect clipBounds;
    private int[] centers;

    private Rect[] rects;

    public Type() {
    }

    public Type(Rect clipBounds) {
        rects = calculateRects(clipBounds, null);
    }

    public Type(Rect clipBounds, int[] centers) {
        rects = calculateRects(clipBounds, centers);
    }

    public abstract Rect[] calculateRects(Rect clipBounds, int[] dividers);

    public Rect[] getRects() {
        return rects;
    }

    public Rect getClipBounds() {
        return clipBounds;
    }

    public void setClipBounds(Rect clipBounds) {
        this.clipBounds = clipBounds;
        if (clipBounds != null) {
            rects = calculateRects(clipBounds, new int[4]);
        }
    }

    public int[] getCenters() {
        return centers;
    }

    public void setCenters(int[] centers) {
        this.centers = centers;
        if (clipBounds != null && centers != null) {
            rects = calculateRects(clipBounds, centers);
        }
    }

}
