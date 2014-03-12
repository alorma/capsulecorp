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
import cat.alorma.capsulecorp.library.distributor.Distributor;
import cat.alorma.capsulecorp.library.type.TypeFactory;
import cat.alorma.capsulecorp.library.type.Type;

/**
 * Created by Bernat on 25/11/13.
 */
public class DispenserViewOld extends View implements Capsule.CapsuleListener {

    protected Paint paint;
    protected Bitmap result;
    protected Bitmap original;
    private Distributor distribuidor;

    public DispenserViewOld(Context context) {
        super(context);
        init(null);
    }

    public DispenserViewOld(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public DispenserViewOld(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        getAttributes(attrs);
        isInEditMode();
        paint = new Paint();
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
                    // TODO MASK
                    // TODO DIVIDER
                } finally {
                    a.recycle();
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (distribuidor != null) {

            distribuidor.getType().calculateRects(canvas.getClipBounds(), null, null);

            // TypeFactory.setData(this, distribuidor.getType(), distribuidor.getDivider());

            if (result == null && original == null) {
                result = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
                original = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            }

            canvas.save();

            Canvas originalImage = new Canvas(original);
            // Rect bounds = getBoundPaint(canvas);
            // drawCapsules(originalImage);

            distribuidor.draw(canvas, paint);

            /*
            drawDividers(originalImage, bounds);
            drawMask(canvas, bounds);
            */

            canvas.restore();

            result = null;
            original = null;
        }
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
        if (distribuidor != null) {
            distribuidor.drawDividers(canvas, bounds);
        }
    }

    private void drawCapsules(Canvas canvas) {
        if (distribuidor != null) {

        }
    }

    private void drawCapsule(Canvas canvas, Capsule capsule, Rect rect) {
        if (canvas != null && capsule != null && rect != null) {
            capsule.boom(canvas, paint, rect);
        }
    }

    private void drawMask(Canvas canvas, Rect bounds) {
        Bitmap mask = null;
        Bitmap background = null;
        Canvas mCanvas = new Canvas(result);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        /*if (this.maskEnabled && maskResource != -1) {
            mask = BitmapFactory.decodeResource(getResources(), maskResource);
            mask = Bitmap.createScaledBitmap(mask, bounds.width(), bounds.height(), true);
            // mask.eraseColor(Color.TRANSPARENT);
        }
        if (this.backgroundMaskEnabled && this.BackgroundMaskResource != -1) {
            background = BitmapFactory.decodeResource(getResources(), BackgroundMaskResource);
            background = Bitmap.createScaledBitmap(background, bounds.width(), bounds.height(), true);

        }*/

        if (mask != null) {
            Rect padding = getBoundPadding(canvas);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            mCanvas.drawBitmap(original, 0, 0, null);
            mCanvas.drawBitmap(mask, padding.left, padding.top, paint);
            if (background != null) {
                mCanvas.drawBitmap(background, padding.left, padding.top, null);
            }
            paint.setXfermode(null);
        } else {
            mCanvas.drawBitmap(original, 0, 0, paint);
        }

        canvas.drawBitmap(result, new Matrix(), new Paint());


    }

    @Override
    public void requestInvalidate() {
        postInvalidate();
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        postInvalidate();
    }

    public void setPadding(int padding) {
        setPadding(padding, padding, padding, padding);
        postInvalidate();
    }

    public void setDistribuidor(Distributor distribuidor) {
        this.distribuidor = distribuidor;
    }
}