package cat.alorma.capsules.ui.capsules;

import android.graphics.Rect;

import cat.alorma.capsulecorp.library.type.Type;

/**
 * Created by Bernat on 7/02/14.
 */
public class CustomType extends Type {

    @Override
    public Rect[] calculateRects(Rect clipBounds, Rect centers, Rect paddings) {

        int size = clipBounds.right - clipBounds.left;
        int terc1 = size / 3 + clipBounds.left;
        int terc2 = (size / 3) * 2 + clipBounds.left;

        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, terc1, clipBounds.bottom);
        Rect rect2 = new Rect(terc1, clipBounds.top, terc2, clipBounds.bottom);
        Rect rect3 = new Rect(terc2 , clipBounds.top, clipBounds.right, clipBounds.bottom);

        return new Rect[]{rect1, rect2, rect3};
    }

    @Override
    public int size() {
        return 3;
    }
}
