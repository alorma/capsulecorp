package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;


/**
 * Created by Bernat on 7/02/14.
 */
public class TypeFour extends Type {

    public TypeFour(Rect[] rects) {
        super(rects);
    }

    public TypeFour() {
        super();
    }

    @Override
    public Type calculateRects(Rect clipBounds, Rect centers, Rect paddings) {
        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, clipBounds.width() / 2, clipBounds.height() / 2);
        Rect rect2 = new Rect(clipBounds.width() / 2, clipBounds.top, clipBounds.width(), clipBounds.height() / 2);
        Rect rect3 = new Rect(clipBounds.left, clipBounds.height() / 2, clipBounds.width() / 2, clipBounds.bottom);
        Rect rect4 = new Rect(clipBounds.width() / 2, clipBounds.height() / 2, clipBounds.width(), clipBounds.bottom);

        this.setRects(new Rect[]{rect1, rect2, rect3, rect4});
        return this;
    }

    @Override
    public int size() {
        return 4;
    }
}
