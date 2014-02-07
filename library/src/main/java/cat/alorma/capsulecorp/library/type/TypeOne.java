package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;

import cat.alorma.capsulecorp.library.viewhelpers.Centers;
import cat.alorma.capsulecorp.library.viewhelpers.Paddings;

/**
 * Created by Bernat on 7/02/14.
 */
public class TypeOne extends Type {

    @Override
    public Rect[] calculateRects(Rect clipBounds, Centers centers, Paddings paddings) {
        return new Rect[] {clipBounds};
    }

    @Override
    public int size() {
        return 1;
    }
}
