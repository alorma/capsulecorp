package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;


/**
 * Created by Bernat on 7/02/14.
 */
public class TypeOne extends Type {

    @Override
    public Rect[] calculateRects(Rect clipBounds, Rect centers, Rect paddings) {
        return new Rect[] {clipBounds};
    }

    @Override
    public int size() {
        return 1;
    }
}
