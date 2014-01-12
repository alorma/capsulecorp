package cat.alorma.capsulecorp.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import java.util.List;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;

/**
 * Created by Bernat on 25/11/13.
 */
public class DispenserView extends View implements Capsule.CapsuleListener {

    private static final int MAX_CAPSULES = 4;
    private SparseArray<Capsule> capsules;
    private SparseArray<Rect[]> rects;
    private Paint paint;

    public DispenserView(Context context) {
        super(context);
        init();
    }

    public DispenserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DispenserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        init(null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int max = Math.max(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(max, max);
        setMeasuredDimension(max, max);
    }

    public void init(List<Capsule> capsulesList) {
        isInEditMode();
        capsules = new SparseArray<Capsule>();
        paint = new Paint();

        if (rects == null) {
            rects = new SparseArray<Rect[]>();

        }

        if (capsulesList != null) {
            for (int i = 0; i < (capsulesList.size() <= MAX_CAPSULES ? capsulesList.size() : MAX_CAPSULES); i++) {
                addCapsule(i, capsulesList.get(i));
            }
        } else {
            int black = Color.parseColor("#000000");
            int white = Color.parseColor("#FFFFFF");
            addCapsule(0, new TextCapsule("C", white, black));
        }
    }

    public void addCapsule(int capsulePosition, Capsule capsule) {
        if (capsules == null) {
            capsules = new SparseArray<Capsule>();
        }
        if (capsule != null) {
            if (capsulePosition >= 0 && capsulePosition <= MAX_CAPSULES) {
                capsule.setCapsuleListener(this);
                capsules.put(capsulePosition, capsule);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.save();
        calculateRects(canvas);

        if (capsules != null && capsules.size() > 0) {
            switch (capsules.size()) {
                case 1:
                    drawCapsule(canvas, capsules.get(0), rects.get(0)[0]);
                    break;
                case 2:
                    drawCapsule(canvas, capsules.get(0), rects.get(1)[0]);
                    drawCapsule(canvas, capsules.get(1), rects.get(1)[1]);
                    break;
                case 3:
                    drawCapsule(canvas, capsules.get(0), rects.get(2)[0]);
                    drawCapsule(canvas, capsules.get(1), rects.get(2)[1]);
                    drawCapsule(canvas, capsules.get(2), rects.get(2)[2]);
                    break;
                case 4:
                    drawCapsule(canvas, capsules.get(0), rects.get(3)[0]);
                    drawCapsule(canvas, capsules.get(1), rects.get(3)[1]);
                    drawCapsule(canvas, capsules.get(2), rects.get(3)[2]);
                    drawCapsule(canvas, capsules.get(3), rects.get(3)[3]);
                    break;
            }
            canvas.restore();
        }
    }

    private void calculateRects(Canvas canvas) {
        Rect clipBounds = canvas.getClipBounds();

        // Draw one capsule
        if (rects.get(0) == null) {
            rects.put(0, new Rect[]{clipBounds});
        }

        int left = clipBounds.left;
        int right = clipBounds.right;
        int top = clipBounds.top;
        int bottom = clipBounds.bottom;

        int centerX = clipBounds.width() / 2;
        int centerY = clipBounds.height() / 2;

        // Draw two capsules
        if (rects.get(1) == null) {
            Rect rect1 = new Rect(left, top, centerX, bottom);
            Rect rect2 = new Rect(centerX, top, right, bottom);

            rects.put(1, new Rect[]{rect1, rect2});
        }

        // Draw three capsules
        if (rects.get(2) == null) {
            Rect rect1 = new Rect(left, top, centerX, bottom);
            Rect rect2 = new Rect(centerX, top, right, centerY);
            Rect rect3 = new Rect(centerX, centerY, right, bottom);

            rects.put(2, new Rect[]{rect1, rect2, rect3});
        }

        // Draw four capsules
        if (rects.get(3) == null) {
            Rect rect1 = new Rect(left, top, centerX, centerY);
            Rect rect2 = new Rect(centerX, top, right, centerY);
            Rect rect3 = new Rect(left, centerY, centerX, bottom);
            Rect rect4 = new Rect(centerX, centerY, right, bottom);

            rects.put(3, new Rect[]{rect1, rect2, rect3, rect4});
        }
    }

    private void drawCapsule(Canvas canvas, Capsule capsule, Rect rect) {
        if (canvas != null && capsule != null && rect != null) {
            capsule.boom(canvas, paint, rect);
        }
    }

    public void clear() {
        capsules.clear();
        invalidate();
    }

    @Override
    public void invalidateParent() {
        invalidate();
    }
}
