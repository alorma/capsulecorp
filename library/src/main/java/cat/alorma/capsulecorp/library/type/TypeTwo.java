package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;

import cat.alorma.capsulecorp.library.viewhelpers.Centers;
import cat.alorma.capsulecorp.library.viewhelpers.Paddings;

/**
 * Created by Bernat on 7/02/14.
 */
public class TypeTwo extends Type {

    public TypeTwo() {

    }

    @Override
    public Rect[] calculateRects(Rect clipBounds, Centers centers, Paddings paddings) {
        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centers.getCenterXL(), clipBounds.bottom);
        Rect rect2 = new Rect(centers.getCenterXR(), clipBounds.top, clipBounds.right, clipBounds.bottom);

        return new Rect[] {rect1, rect2};
    }

    @Override
    public int size() {
        return 2;
    }


}
