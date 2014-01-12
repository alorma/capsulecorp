package cat.alorma.capsulecorp.library.capsule.abs;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Bernat on 12/01/14.
 */
interface CapsuleDrawer {
   void create(Canvas canvas, Paint paint, Rect rect);
}
