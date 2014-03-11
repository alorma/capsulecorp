package cat.alorma.capsulecorp.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;

/**
 * Created by Bernat on 11/03/14.
 */
public class CapsuleView extends View {
    private Capsule capsule;
    private Paint paint;

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

        if (capsule != null) {
            capsule.boom(canvas, paint, canvas.getClipBounds());
        }
    }
}
