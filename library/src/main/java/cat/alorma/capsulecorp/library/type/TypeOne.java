package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;


/**
 * Created by Bernat on 7/02/14.
 */
public class TypeOne extends Type {

    public TypeOne(Rect[] rects) {
        super(rects);
    }

    public TypeOne() {
        super();
    }

    @Override
    public Type calculateRects(Rect clipBounds, Rect centers, Rect paddings) {
        this.setRects(new Rect[] {clipBounds});
        return this;
    }

    @Override
    public int size() {
        return 1;
    }
}
