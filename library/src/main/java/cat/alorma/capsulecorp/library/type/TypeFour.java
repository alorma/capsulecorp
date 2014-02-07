package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;

/**
 * Created by Bernat on 7/02/14.
 */
public class TypeFour extends Type {

    @Override
    public Rect[] calculateRects(Rect clipBounds, int[] dividers) {
        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, dividers[0], dividers[2]);
        Rect rect2 = new Rect(dividers[1], clipBounds.top, clipBounds.right, dividers[2]);
        Rect rect3 = new Rect(clipBounds.left, dividers[3], dividers[0], clipBounds.bottom);
        Rect rect4 = new Rect(dividers[1], dividers[3], clipBounds.right, clipBounds.bottom);

        return new Rect[]{rect1, rect2, rect3, rect4};
    }
}
