package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;

import cat.alorma.capsulecorp.library.viewhelpers.Centers;

/**
 * Created by adria on 6/02/14.
 */
public abstract class Type {
    private Rect clipBounds;
    private Centers centers;

    private Rect[] rects;

    public Type() {
    }

    public Type(Rect clipBounds) {
        rects = calculateRects(clipBounds, null);
    }

    public Type(Rect clipBounds, Centers centers) {
        rects = calculateRects(clipBounds, centers);
    }

    public abstract Rect[] calculateRects(Rect clipBounds, Centers centers);

    public Rect[] getRects() {
        return rects;
    }

    public Rect getClipBounds() {
        return clipBounds;
    }

    public void setClipBounds(Rect clipBounds) {
        this.clipBounds = clipBounds;
        if (clipBounds != null) {
            rects = calculateRects(clipBounds, new Centers());
        }
    }

    public Centers getCenters() {
        return centers;
    }

    public void setCenters(Centers centers) {
        this.centers = centers;
        if (clipBounds != null && centers != null) {
            rects = calculateRects(clipBounds, centers);
        }
    }

    public abstract int size();

}
