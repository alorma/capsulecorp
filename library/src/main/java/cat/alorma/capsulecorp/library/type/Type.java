package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;

import cat.alorma.capsulecorp.library.viewhelpers.Centers;
import cat.alorma.capsulecorp.library.viewhelpers.Paddings;

/**
 * Created by adria on 6/02/14.
 */
public abstract class Type {
    private Rect clipBounds;
    private Centers centers;

    private Rect[] rects;
    private Paddings paddings;

    public Type() {
    }

    public Type(Rect clipBounds) {
        rects = calculateRects(clipBounds, null, null);
    }

    public Type(Rect clipBounds, Centers centers, Paddings paddings) {
        rects = calculateRects(clipBounds, centers, paddings);
    }

    public abstract Rect[] calculateRects(Rect clipBounds, Centers centers, Paddings paddings);

    public Rect[] getRects() {
        return rects;
    }

    public Rect getClipBounds() {
        return clipBounds;
    }

    public void setClipBounds(Rect clipBounds) {
        this.clipBounds = clipBounds;
        if (clipBounds != null) {
            rects = calculateRects(clipBounds, new Centers(), null);
        }
    }

    public Centers getCenters() {
        return centers;
    }

    public void setCenters(Centers centers) {
        this.centers = centers;
        if (clipBounds != null && centers != null) {
            rects = calculateRects(clipBounds, centers, null);
        }
    }

    public abstract int size();

    public void setPaddings(Paddings paddings) {
        this.paddings = paddings;
    }

    public Paddings getPaddings() {
        return paddings;
    }
}
