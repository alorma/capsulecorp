package cat.alorma.capsulecorp.library.capsule.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;

/**
 * Created by Bernat on 25/11/13.
 */
public abstract class BitmapCapsule extends Capsule {

    protected BitmapCapsule() {
    }

    @Override
    public void boom(Canvas canvas,Paint paint, Rect rect) {
        Bitmap bitmap = getBitmap();
        if (bitmap != null) {
            paint.setStyle(Paint.Style.FILL);
            canvas.drawBitmap(bitmap, null, rect, paint);
        }
    }

    protected abstract Bitmap getBitmap();

}
