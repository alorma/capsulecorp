package cat.alorma.capsulecorp.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;

import java.util.List;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;

/**
 * Created by Bernat on 25/11/13.
 */
public class DispenserView extends View implements Capsule.CapsuleListener {

    private static final int MAX_CAPSULES = 4;
    private SparseArray<Capsule> capsules;
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

    public void init(List<Capsule> capsulesList) {
        capsules = new SparseArray<Capsule>();
        paint = new Paint();
        if (capsulesList != null) {
            for (int i = 0; i < (capsulesList.size() <= MAX_CAPSULES ? capsulesList.size() : MAX_CAPSULES); i++) {
                addCapsule(i, capsulesList.get(i));
            }
        }
    }

    public void addCapsule(int capsulePosition, Capsule capsule) {
        if (capsules == null) {
            capsules = new SparseArray<Capsule>();
        }
        if (capsule != null) {
            if (capsulePosition > 0 && capsulePosition < MAX_CAPSULES) {
                capsule.setCapsuleListener(this);
                capsules.put(capsulePosition, capsule);
            }
            invalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();

        if (capsules.size() > 0) {
            switch (capsules.size()) {
                case 1:
                    drawOneCapsule(canvas, capsules.get(0));
                    break;
                case 2:
                    drawTwoCapsule(canvas, capsules.get(0), capsules.get(1));
                    break;
                case 3:
                    drawThreeCapsule(canvas, capsules.get(0), capsules.get(1), capsules.get(2));
                    break;
                case 4:
                    drawFourCapsule(canvas, capsules.get(0), capsules.get(1), capsules.get(2), capsules.get(3));
                    break;
            }
            canvas.restore();
        }
    }

    private void drawOneCapsule(Canvas canvas, Capsule capsule) {
        Rect clipBounds = canvas.getClipBounds();
        capsule.boom(canvas, paint, clipBounds);
    }

    private void drawTwoCapsule(Canvas canvas, Capsule capsule1, Capsule capsule2) {
        Rect clipBounds = canvas.getClipBounds();

        int left = clipBounds.left;
        int right = clipBounds.right;
        int top = clipBounds.top;
        int bottom = clipBounds.bottom;

        int centerX = clipBounds.width() / 2;

        Rect rect1 = new Rect(left, top, centerX, bottom);
        Rect rect2 = new Rect(centerX, top, right, bottom);

        capsule1.boom(canvas, paint, rect1);
        capsule2.boom(canvas, paint, rect2);

    }

    private void drawThreeCapsule(Canvas canvas, Capsule capsule1, Capsule capsule2, Capsule capsule3) {
        Rect clipBounds = canvas.getClipBounds();

        int left = clipBounds.left;
        int right = clipBounds.right;
        int top = clipBounds.top;
        int bottom = clipBounds.bottom;

        int centerX = clipBounds.width() / 2;
        int centerY = clipBounds.height() / 2;

        Rect rect1 = new Rect(left, top, centerX, bottom);
        Rect rect2 = new Rect(centerX, top, right, centerY);
        Rect rect3 = new Rect(centerX, centerY, right, bottom);

        capsule1.boom(canvas, paint, rect1);
        capsule2.boom(canvas, paint, rect2);
        capsule3.boom(canvas, paint, rect3);
    }

    private void drawFourCapsule(Canvas canvas, Capsule capsule1, Capsule capsule2, Capsule capsule3, Capsule capsule4) {
        Rect clipBounds = canvas.getClipBounds();

        int left = clipBounds.left;
        int right = clipBounds.right;
        int top = clipBounds.top;
        int bottom = clipBounds.bottom;

        int centerX = clipBounds.width() / 2;
        int centerY = clipBounds.height() / 2;

        Rect rect1 = new Rect(left, top, centerX, centerY);
        Rect rect2 = new Rect(centerX, top, right, centerY);
        Rect rect3 = new Rect(left, centerY, centerX, bottom);
        Rect rect4 = new Rect(centerX, centerY, right, bottom);

        capsule1.boom(canvas, paint, rect1);
        capsule2.boom(canvas, paint, rect2);
        capsule3.boom(canvas, paint, rect3);
        capsule4.boom(canvas, paint, rect4);
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
