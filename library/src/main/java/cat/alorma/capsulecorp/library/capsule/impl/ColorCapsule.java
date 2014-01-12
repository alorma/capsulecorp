package cat.alorma.capsulecorp.library.capsule.impl;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;

/**
 * Created by Bernat on 25/11/13.
 */
public class ColorCapsule extends Capsule {

    protected int background;

    public ColorCapsule(int background) {
        this.background = background;
    }

    @Override
    public void create(Canvas canvas, Paint paint, Rect rect) {
        paint.setColor(this.background);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect, paint);
    }
}
