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
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;
import cat.alorma.capsulecorp.library.type.TypeFactory;
import cat.alorma.capsulecorp.library.type.Type;

/**
 * Created by Bernat on 25/11/13.
 */
public class DispenserView extends View implements Capsule.CapsuleListener {

    private static final int MAX_CAPSULES = 4;
    private ArrayList<Capsule> capsules;
    private Type concretType;
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

    private void init(AttributeSet attrs) {
        init(attrs, null);
    }

    public void init(AttributeSet attrs, List<Capsule> capsulesList) {
        getAttributes(attrs);
        isInEditMode();
        capsules = new ArrayList<Capsule>();
        paint = new Paint();

        if (capsulesList != null) {
            for (int i = 0; i < (capsulesList.size() <= MAX_CAPSULES ? capsulesList.size() : MAX_CAPSULES); i++) {
                addCapsule(capsulesList.get(i));
            }
        } else {
            int black = Color.parseColor("#000000");
            int white = Color.parseColor("#FFFFFF");
            addCapsule(new TextCapsule("C", white, black));
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int max = Math.max(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(max, max);
        setMeasuredDimension(max, max);
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

    private Type getConcretType() {
        concretType = concretType != null
                ? concretType
                : TypeFactory.newInstance(this, capsules.size(), divider_size);
        return concretType;
    }

    public void setConcretType(Type concretType) {
        this.concretType = concretType;

    }

    public int getMaskResource() {
        return maskResource;
    }

    public int getDividerSize() {
        return divider_size;
    }

    public void setDividerSize(int divider_size) {
        this.divider_size = divider_size;
        postInvalidate();
    }

    public int getDividerColor() {
        return divider_color;
    }

    public void setDividerColor(int divider_color) {
        this.divider_color = divider_color;
        postInvalidate();
    }

    public boolean isMaskEnabled() {
        return maskEnabled;
    }

    public void setMaskEnabled(boolean mask) {
        this.maskEnabled = mask;
        postInvalidate();
    }

    public void setMaskResource(int maskResource) {
        this.maskResource = maskResource;
        postInvalidate();
    }

    public void addCapsule(Capsule capsule) {
        if (capsules == null) {
            capsules = new ArrayList<Capsule>();
        }
        if (capsule != null) {
            capsule.setCapsuleListener(this);
            capsules.add(capsules.size(), capsule);
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

        getConcretType();

        Rect bounds = getBoundPaint(canvas);
        drawDividers(canvas, bounds);
        drawCapsules(original);
        drawMask(canvas, bounds);

        canvas.restore();

        result = null;
        original = null;
    }

    private Rect getBoundPaint(Canvas canvas) {
        Rect clipBounds = canvas.getClipBounds();

        Rect padding = getBoundPadding(canvas);

        clipBounds.left = padding.left;
        clipBounds.right = clipBounds.right - padding.right;
        clipBounds.top = padding.top;
        clipBounds.bottom = clipBounds.bottom - padding.bottom;

        return clipBounds;
    }

    private Rect getBoundPadding(Canvas canvas) {
        Rect clipBounds = canvas.getClipBounds();
        int paddingLeft = getPaddingLeft() < clipBounds.width() / 2 ? getPaddingLeft() : 0;
        int paddingRight = getPaddingRight() < clipBounds.width() / 2 ? getPaddingRight() : 0;
        int paddingTop = getPaddingTop() < clipBounds.height() / 2 ? getPaddingTop() : 0;
        int paddingBottom = getPaddingBottom() < clipBounds.height() / 2 ? getPaddingBottom() : 0;

        Rect rect = new Rect(paddingLeft, paddingTop, paddingRight, paddingBottom);
        return rect;
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
        Rect[] rects = getConcretType().getRects();
        if (capsules != null && size() > 0) {
            for (int i = 0; i < size(); i++) {
                Capsule capsule = capsules.get(i);
                drawCapsule(originalImage, capsule, rects[i]);
                Log.i("RECT", rects[i].toShortString());
            }
        }
    }

    private void drawCapsule(Canvas canvas, Capsule capsule, Rect rect) {
        if (canvas != null && capsule != null && rect != null) {
            capsule.boom(canvas, paint, rect);
        }
    }

    private void drawMask(Canvas canvas, Rect bounds) {
        Bitmap mask = null;
        Canvas mCanvas = new Canvas(result);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        if (this.maskEnabled && maskResource != -1) {
            mask = BitmapFactory.decodeResource(getResources(), maskResource);
            mask = Bitmap.createScaledBitmap(mask, bounds.width(), bounds.height(), true);
            // mask.eraseColor(Color.TRANSPARENT);
        }

        if (mask != null) {
            Rect padding = getBoundPadding(canvas);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            mCanvas.drawBitmap(original, 0, 0, null);
            mCanvas.drawBitmap(mask, padding.left, padding.top, paint);
            paint.setXfermode(null);
        } else {
            mCanvas.drawBitmap(original, 0, 0, paint);
        }

        canvas.drawBitmap(result, new Matrix(), new Paint());
    }

    private int size() {
        int size = capsules.size() <= MAX_CAPSULES ? capsules.size() : MAX_CAPSULES;
        size = size <= getConcretType().size() ? size : getConcretType().size();
        return size;
    }

    public void clear() {
        capsules.clear();
        concretType = null;
        postInvalidate();
    }

    @Override
    public void invalidateParent() {
        invalidate();
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        concretType = null;
        invalidate();
    }

    public void setPadding(int padding) {
        setPadding(padding, padding, padding, padding);
        postInvalidate();
    }
}