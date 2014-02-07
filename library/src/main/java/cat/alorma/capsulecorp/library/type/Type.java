package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;


/**
 * Created by adria on 6/02/14.
 */
public abstract class Type {
    private Rect clipBounds;
    private Rect centers;

    private Rect[] rects;
    private Rect paddings;

    public Type() {
    }

    public Type(Rect clipBounds) {
        rects = calculateRects(clipBounds, null, null);
    }

    public Type(Rect clipBounds, Rect centers, Rect paddings) {
        rects = calculateRects(clipBounds, centers, paddings);
    }

    public abstract Rect[] calculateRects(Rect clipBounds, Rect centers, Rect paddings);

    public Rect[] getRects() {
        return rects;
    }

    public Rect getClipBounds() {
        return clipBounds;
    }

    public void setClipBounds(Rect clipBounds) {
        this.clipBounds = clipBounds;
        if (clipBounds != null) {
            rects = calculateRects(clipBounds, new Rect(), null);
        }
    }

    public Rect getCenters() {
        return centers;
    }

    public void setCenters(Rect centers) {
        this.centers = centers;
        if (clipBounds != null && centers != null) {
            rects = calculateRects(clipBounds, centers, null);
        }
    }

    public abstract int size();

    public void setPaddings(Rect paddings) {
        this.paddings = paddings;
    }

    public Rect getPaddings() {
        return paddings;
    }
}
