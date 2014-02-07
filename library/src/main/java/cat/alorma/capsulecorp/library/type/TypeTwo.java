package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;

/**
 * Created by Bernat on 7/02/14.
 */
public class TypeTwo extends Type {

    public TypeTwo() {

    }

    @Override
    public Rect[] calculateRects(Rect clipBounds, int[] dividers) {
        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, dividers[0], clipBounds.bottom);
        Rect rect2 = new Rect(dividers[1], clipBounds.top, clipBounds.right, clipBounds.bottom);

        return new Rect[] {rect1, rect2};
    }


}
