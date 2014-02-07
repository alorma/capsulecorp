package cat.alorma.capsulecorp.library.capsulestype;

import android.graphics.Rect;

/**
 * Created by Bernat on 7/02/14.
 */
public class TypeOne extends Type {

    @Override
    public Rect[] calculateRects(Rect clipBounds, int[] dividers) {
        return new Rect[] {clipBounds};
    }
}
