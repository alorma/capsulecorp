package cat.alorma.capsulecorp.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
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
    private int maskResource = -1;
    private boolean maskEnabled = false;
    private Bitmap result;
    private Bitmap original;
    private int divider_color;
    private int divider_size;

    public DispenserView(Context context) {
        super(context);
        init(null);
    }

    public DispenserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DispenserView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int max = Math.max(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(max, max);
        setMeasuredDimension(max, max);
        rects = null;
    }

    private void init(AttributeSet attrs) {
        init(attrs, null);
    }

    public void init(AttributeSet attrs, List<Capsule> capsulesList) {
        getAttributes(attrs);
        isInEditMode();
        capsules = new SparseArray<Capsule>();
        paint = new Paint();

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

    private void getAttributes(AttributeSet attrs) {

        if (getContext() != null && getContext().getTheme() != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.dispenserattrs, 0, 0);

            if (a != null) {
                try {
                    maskEnabled = a.getBoolean(R.styleable.dispenserattrs_mask_enabled, false);
                    maskResource = a.getResourceId(R.styleable.dispenserattrs_mask, -1);
                    divider_color = a.getInt(R.styleable.dispenserattrs_divider_color, -1);
                    if (divider_color == -1) {
                        divider_color = Color.parseColor("#EAEAEA");
                    }
                    divider_size = Utils.dividerSize(getContext(), a);
                } finally {
                    a.recycle();
                }
            }
        }
    }

    public int getMaskResource() {
        return maskResource;
    }

    public int getDivider_size() {
        return divider_size;
    }

    public void setDivider_size(int divider_size) {
        this.divider_size = divider_size;
        rects = null;
        postInvalidate();
    }

    public int getDivider_color() {
        return divider_color;
    }

    public void setDivider_color(int divider_color) {
        this.divider_color = divider_color;
        rects = null;
        postInvalidate();
    }

    public void setMaskEnabled(boolean mask) {
        this.maskEnabled = mask;
        postInvalidate();
    }
    
    public void setMaskResource(int maskResource) {
        this.maskResource = maskResource;
        postInvalidate();
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

        if (result == null && original == null) {
            result = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            original = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        }

        canvas.save();

        Rect bounds = calculateRects(canvas);
        drawDividers(canvas, bounds);
        drawCapsules(original);
        drawMask(canvas);

        canvas.restore();

        result = null;
        original = null;
    }

    private void drawDividers(Canvas canvas, Rect bounds) {
        if (divider_size > 0 || divider_color != -1) {
            Paint dividerPaint = new Paint();
            dividerPaint.setStyle(Paint.Style.FILL);
            dividerPaint.setColor(divider_color);
            canvas.drawRect(bounds, dividerPaint);
        }
    }

    private void drawCapsules(Bitmap original) {
        Canvas originalImage = new Canvas(original);
        if (capsules != null && capsules.size() > 0) {
            switch (capsules.size()) {
                case 1:
                    drawCapsule(originalImage, capsules.get(0), rects.get(0)[0]);
                    break;
                case 2:
                    drawCapsule(originalImage, capsules.get(0), rects.get(1)[0]);
                    drawCapsule(originalImage, capsules.get(1), rects.get(1)[1]);
                    break;
                case 3:
                    drawCapsule(originalImage, capsules.get(0), rects.get(2)[0]);
                    drawCapsule(originalImage, capsules.get(1), rects.get(2)[1]);
                    drawCapsule(originalImage, capsules.get(2), rects.get(2)[2]);
                    break;
                case 4:
                    drawCapsule(originalImage, capsules.get(0), rects.get(3)[0]);
                    drawCapsule(originalImage, capsules.get(1), rects.get(3)[1]);
                    drawCapsule(originalImage, capsules.get(2), rects.get(3)[2]);
                    drawCapsule(originalImage, capsules.get(3), rects.get(3)[3]);
                    break;
            }
        }
    }

    private void drawMask(Canvas canvas) {
        Bitmap mask = null;
        Canvas mCanvas = new Canvas(result);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (this.maskEnabled && maskResource != -1) {
            mask = BitmapFactory.decodeResource(getResources(), maskResource);
            mask = Bitmap.createScaledBitmap(mask, original.getWidth(), original.getHeight(), true);
        }

        if (mask != null) {
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            mCanvas.drawBitmap(original, 0, 0, null);
            mCanvas.drawBitmap(mask, 0, 0, paint);
            paint.setXfermode(null);
        } else {
            mCanvas.drawBitmap(original, 0, 0, paint);
        }

        canvas.drawBitmap(result, new Matrix(), new Paint());
    }

    private Rect calculateRects(Canvas canvas) {
        Rect clipBounds = canvas.getClipBounds();
        if (rects == null) {
            rects = new SparseArray<Rect[]>(4);

            int paddingLeft = getPaddingLeft() < clipBounds.width() / 2 ? getPaddingLeft() : 0;
            int paddingRight = getPaddingRight() < clipBounds.width() / 2 ? getPaddingRight() : 0;
            int paddingTop = getPaddingTop() < clipBounds.height() / 2 ? getPaddingTop() : 0;
            int paddingBottom = getPaddingBottom() < clipBounds.height() / 2 ? getPaddingBottom() : 0;

            clipBounds.left = paddingLeft;
            clipBounds.right = clipBounds.right - paddingRight;
            clipBounds.top = paddingTop;
            clipBounds.bottom = clipBounds.bottom - paddingBottom;

            int centerX = (clipBounds.width() / 2) + paddingLeft;
            int centerY = (clipBounds.height() / 2) + paddingTop;

            int centerXL = centerX;
            int centerXR = centerX;
            int centerYT = centerY;
            int centerYB = centerY;

            if (divider_size != -1) {
                centerXL = centerXL - (divider_size / 2);
                centerXR = centerXR + (divider_size / 2);
                centerYT = centerYT - (divider_size / 2);
                centerYB = centerYB + (divider_size / 2);
            }

            // Draw one capsule
            if (rects.get(0) == null) {
                rects.put(0, new Rect[]{clipBounds});
            }

            // Draw two capsules
            if (rects.get(1) == null) {
                Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centerXL, clipBounds.bottom);
                Rect rect2 = new Rect(centerXR, clipBounds.top, clipBounds.right, clipBounds.bottom);

                rects.put(1, new Rect[]{rect1, rect2});
            }

            // Draw three capsules
            if (rects.get(2) == null) {
                Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centerXL, clipBounds.bottom);
                Rect rect2 = new Rect(centerXR, clipBounds.top, clipBounds.right, centerYT);
                Rect rect3 = new Rect(centerXR, centerYB, clipBounds.right, clipBounds.bottom);

                rects.put(2, new Rect[]{rect1, rect2, rect3});
            }

            // Draw four capsules
            if (rects.get(3) == null) {
                Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centerXL, centerYT);
                Rect rect2 = new Rect(centerXR, clipBounds.top, clipBounds.right, centerYT);
                Rect rect3 = new Rect(clipBounds.left, centerYB, centerXL, clipBounds.bottom);
                Rect rect4 = new Rect(centerXR, centerYB, clipBounds.right, clipBounds.bottom);

                rects.put(3, new Rect[]{rect1, rect2, rect3, rect4});
            }
        }
        return clipBounds;
    }

    private void drawCapsule(Canvas canvas, Capsule capsule, Rect rect) {
        if (canvas != null && capsule != null && rect != null) {
            capsule.boom(canvas, paint, rect);
        }
    }

    public void clear() {
        capsules.clear();
        rects = null;
        postInvalidate();
    }

    @Override
    public void invalidateParent() {
        invalidate();
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        rects = null;
        invalidate();
    }

    public void setPadding(int padding) {
        setPadding(padding, padding, padding, padding);
    }
}
