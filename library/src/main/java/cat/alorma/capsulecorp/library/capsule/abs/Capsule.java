package cat.alorma.capsulecorp.library.capsule.abs;

import android.graphics.Canvas;

import android.graphics.Paint;
import android.graphics.Rect;
/**
 * Created by Bernat on 25/11/13.
 */
public abstract class Capsule {

    private CapsuleListener capsuleListener;

    public abstract void boom(Canvas canvas, Paint paint, Rect rect);

    public void setCapsuleListener(CapsuleListener capsuleListener) {
        this.capsuleListener = capsuleListener;
    }

    public CapsuleListener getCapsuleListener() {
        return capsuleListener;
    }

    public interface CapsuleListener {
        void invalidateParent();
    }

}
