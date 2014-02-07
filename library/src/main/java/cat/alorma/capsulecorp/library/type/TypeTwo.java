package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;

/**
 * Created by Bernat on 7/02/14.
 */
public class TypeTwo extends Type {

    public TypeTwo() {

    }

    @Override
    public Rect[] calculateRects(Rect clipBounds, Rect centers, Rect paddings) {
        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centers.left, clipBounds.bottom);
        Rect rect2 = new Rect(centers.right, clipBounds.top, clipBounds.right, clipBounds.bottom);

        return new Rect[] {rect1, rect2};
    }

    @Override
    public int size() {
        return 2;
    }


}
