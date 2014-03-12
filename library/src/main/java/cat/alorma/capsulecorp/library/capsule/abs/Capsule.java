package cat.alorma.capsulecorp.library.capsule.abs;

import android.graphics.Canvas;

import android.graphics.Paint;
import android.graphics.Rect;
/**
 * Created by Bernat on 25/11/13.
 */
public abstract class Capsule implements CapsuleDrawer{

    private CapsuleListener capsuleListener;
    private Canvas canvas;
    private Paint paint;
    private Rect rect;
    private boolean enabled = true;

    public void boom(Canvas canvas, Paint paint, Rect rect) {
        this.canvas = canvas;
        this.paint = paint;
        this.rect = rect;

        create(canvas, paint, rect);
    }

    public void setCapsuleListener(CapsuleListener capsuleListener) {
        this.capsuleListener = capsuleListener;
    }

    public CapsuleListener getCapsuleListener() {
        return capsuleListener;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Paint getPaint() {
        return paint;
    }

    public Rect getRect() {
        return rect;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public interface CapsuleListener {
        void requestInvalidate();
    }

}
