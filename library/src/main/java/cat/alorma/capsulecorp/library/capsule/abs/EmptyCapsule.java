package cat.alorma.capsulecorp.library.capsule.abs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by adria on 7/02/14.
 */
public class EmptyCapsule extends Capsule {
    @Override
    public void create(Canvas canvas, Paint paint, Rect rect) {
        paint.setColor(Color.TRANSPARENT);
        canvas.drawRect(rect,paint);
    }
}
