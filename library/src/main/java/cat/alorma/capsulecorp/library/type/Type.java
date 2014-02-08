package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;


/**
 * Created by adria on 6/02/14.
 */
public abstract class Type {

    private Rect[] rects;

    public Type() {
        rects = new Rect[0];
    }
    public Type(Rect [] rect) {
        this.rects = rect;
    }

    public Type(Rect clipBounds) {
        rects = calculateRects(clipBounds, new Rect(), new Rect()).getRects();
    }

    public Type(Rect clipBounds, Rect centers, Rect paddings) {
        rects = calculateRects(clipBounds, centers, paddings).getRects();
    }

    public abstract Type calculateRects(Rect clipBounds, Rect centers, Rect paddings);

    public Rect[] getRects() {
        return rects;
    }

    public void setRects(Rect[] rects){
        this.rects = rects;
    }
    public int size(){
        return rects.length;
    }




}
