package cat.alorma.capsulecorp.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;

/**
 * Created by Bernat on 11/03/14.
 */
public class CapsuleView extends View {
    private Capsule capsule;
    private Paint paint;
    private Rect rect;

    public CapsuleView(Context context) {
        super(context);
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
        this.capsule = new TextCapsule("C", Color.WHITE, Color.BLACK);
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
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (rect == null) {
            rect = canvas.getClipBounds();
        }

        if (capsule != null) {
            capsule.boom(canvas, paint, rect);
        }
    }

    public Rect getRect() {
        return rect;
    }

    public void setRect(Rect rect) {
        if (rect == null) {
            rect = new Rect();
        }

        this.rect = rect;

        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(rect.width(), rect.height());
        } else {
            layoutParams.height = rect.height();
            layoutParams.width = rect.width();
        }

        setLayoutParams(layoutParams);

        invalidate();
    }
}
