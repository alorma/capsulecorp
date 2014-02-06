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

import java.util.ArrayList;
import java.util.List;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;
import cat.alorma.capsulecorp.library.capsulestype.Type;

/**
 * Created by Bernat on 25/11/13.
 */
public class DispenserView extends View implements Capsule.CapsuleListener {

    private static final int MAX_CAPSULES = 4;
    private SparseArray<Capsule> capsules;
    private ArrayList<Type> types;
    private Type concretType;
    private Paint paint;
    private int maskResource = -1;
    private boolean maskEnabled = false;
    private Bitmap result;
    private Bitmap original;
    private int background;


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
        types = Type.getInstance(this);
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

        if (getContext() != null) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.dispenserattrs);

            if (a != null) {
                maskEnabled = a.getBoolean(R.styleable.dispenserattrs_mask_enabled, false);
                maskResource = a.getResourceId(R.styleable.dispenserattrs_mask, -1);
            }
        }
    }

    public Type getConcretType() {
        return concretType;
    }

    public void setConcretType(Type concretType) {
        this.concretType = concretType;
    }

    public int getMaskResource() {
        return maskResource;
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

    public void setMaskEnabled(boolean mask) {
        this.maskEnabled = mask;
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (result == null && original == null) {
            result = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            original = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        }

        canvas.save();

        generateTypes();

        Bitmap mask = null;

        Canvas originalImage = new Canvas(original);

        if (capsules != null && capsules.size() > 0){
            for(int i = 0 ; i < capsules.size(); i++){
                Capsule capsule = capsules.valueAt(i);
                Type type = concretType != null ? concretType : types.get(capsules.size()-1);
                Rect[] rects = type.getRects();
                drawCapsule(originalImage,capsule,rects[i]);
            }

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

            canvas.restore();
        }

        result = null;
        original = null;
    }

    private void generateTypes() {
        types = Type.getInstance(this);
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

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
        types = null;
        invalidate();
    }

    public void setPadding(int padding) {
        setPadding(padding, padding, padding, padding);
    }
}
