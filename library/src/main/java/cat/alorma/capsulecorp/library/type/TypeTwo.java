package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;

/**
 * Created by Bernat on 7/02/14.
 */
public class TypeTwo extends Type {

    public TypeTwo(Rect[] rects) {
        super(rects);
    }

    public TypeTwo() {
        super();
    }

    @Override
    public Type calculateRects(Rect clipBounds, Rect centers, Rect paddings) {
        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centers.left, clipBounds.bottom);
        Rect rect2 = new Rect(centers.right, clipBounds.top, clipBounds.right, clipBounds.bottom);

        this.setRects(new Rect[] {rect1, rect2});
        return this;
    }



}
