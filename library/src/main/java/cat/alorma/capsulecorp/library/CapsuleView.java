package cat.alorma.capsulecorp.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;

/**
 * Created by Bernat on 11/03/14.
 */
public class CapsuleView extends View implements Capsule.CapsuleListener {
    private Capsule capsule;
    private Paint paint;
    private Rect rect;
    private boolean drawed = false;

    public CapsuleView(Context context) {
        super(context);
        init();
    }

    public CapsuleView(Context context, Capsule capsule) {
        super(context);
        this.capsule = capsule;
        init();
    }

    public CapsuleView(Context context, Capsule capsule, Rect rect) {
        super(context);
        this.capsule = capsule;
        setRect(rect);
        init();
    }

    public CapsuleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CapsuleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        isInEditMode();
        if (this.capsule == null) {
            this.capsule = new TextCapsule("C", Color.WHITE, Color.BLACK);
        }

        capsule.setCapsuleListener(this);

        this.paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int max = Math.max(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(max, max);
        setMeasuredDimension(max, max);
    }

    public Capsule getCapsule() {
        return capsule;
    }

    public void setCapsule(Capsule capsule) {
        this.capsule = capsule;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //if (!drawed) {
            Log.i("PRINT", "Capsule is printed " + toString());

            if (rect == null) {
                rect = canvas.getClipBounds();
            }

            if (capsule != null) {
                capsule.boom(canvas, paint, rect);
            }
            drawed = true;
        //}
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        this.rect = rect;
    }

    @Override
    public void requestInvalidate() {
        drawed = false;
        invalidate();
    }
}
