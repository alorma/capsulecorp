package cat.alorma.capsulecorp.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

import cat.alorma.capsulecorp.library.capsule.impl.ColorCapsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;
import cat.alorma.capsulecorp.library.distributor.AbstractCapsulesAdapter;
import cat.alorma.capsulecorp.library.distributor.CapsulesAdapter;

/**
 * Created by Bernat on 11/03/14.
 */
public class DispenserView extends AdapterView<AbstractCapsulesAdapter> {
    private Paint paint;
    private AbstractCapsulesAdapter adapter;

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

    public void init(AttributeSet attrs) {
        getAttributes(attrs);
        if (isInEditMode()) {
          adapter = new CapsulesAdapter();
          adapter.add(new TextCapsule("C", Color.WHITE, Color.BLACK));
        }
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
    public AbstractCapsulesAdapter getAdapter() {
        if (adapter == null) {
            adapter = new CapsulesAdapter();
        }
        return adapter;
    }

    @Override
    public void setAdapter(AbstractCapsulesAdapter adapter) {
        this.adapter = adapter;
        invalidate();
    }

    @Override
    public View getSelectedView() {
        return null;
    }

    @Override
    public void setSelection(int position) {

    }
}
