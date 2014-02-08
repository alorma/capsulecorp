package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;

/**
 * Created by Bernat on 7/02/14.
 */
public class TypeThree extends Type {

    public TypeThree(Rect[] rects) {
        super(rects);
    }

    public TypeThree() {
        super();
    }

    @Override
    public Type calculateRects(Rect clipBounds, Rect centers, Rect paddings) {
        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centers.left, clipBounds.bottom);
        Rect rect2 = new Rect(centers.right, clipBounds.top, clipBounds.right, centers.top);
        Rect rect3 = new Rect(centers.right, centers.bottom, clipBounds.right, clipBounds.bottom);

        this.setRects(new Rect[]{rect1, rect2, rect3});
        return this;
    }


}
