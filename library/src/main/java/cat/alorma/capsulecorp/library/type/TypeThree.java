package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;

import cat.alorma.capsulecorp.library.viewhelpers.Centers;

/**
 * Created by Bernat on 7/02/14.
 */
public class TypeThree extends Type {

    @Override
    public Rect[] calculateRects(Rect clipBounds, Centers centers) {
        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centers.getCenterXL(), clipBounds.bottom);
        Rect rect2 = new Rect(centers.getCenterXR(), clipBounds.top, clipBounds.right, centers.getCenterYT());
        Rect rect3 = new Rect(centers.getCenterXR(), centers.getCenterYB(), clipBounds.right, clipBounds.bottom);

        return new Rect[]{rect1, rect2, rect3};
    }

    @Override
    public int size() {
        return 3;
    }

}
