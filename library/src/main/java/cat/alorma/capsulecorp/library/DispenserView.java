package cat.alorma.capsulecorp.library;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import cat.alorma.capsulecorp.library.distributor.AbstractCapsulesAdapter;

/**
 * Created by Bernat on 11/03/14.
 * <p/>
 * See: http://developer.sonymobile.com/2010/05/20/android-tutorial-making-your-own-3d-list-part-1/
 */
public class DispenserView extends AdapterView<AbstractCapsulesAdapter> {
    private Paint paint;

    private AbstractCapsulesAdapter adapter;
    private SparseArray<CapsuleView> childs;

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
        childs = new SparseArray<CapsuleView>();
        paint = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int max = Math.max(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(max, max);
        setMeasuredDimension(max, max);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (adapter == null) {
            return;
        }

        if (getChildCount() == 0) {
            Log.i("PRINT", "Dispenser print");
            for (int i = 0; i < adapter.getCount(); i++) {
                makeAddView(i);
            }
        }
    }

    private void makeAddView(int position) {
        if (getChildAt(position) == null) {
            CapsuleView child = adapter.getView(position, null, this);
            if (child != null) {
                ViewGroup.LayoutParams p = child.getLayoutParams();
                if (p == null) {
                    p = generateDefaultLayoutParams();
                }
                if (addViewInLayout(child, position, p, false)) {
                    int l = child.getRect().left;
                    int t = child.getRect().top;
                    int r = child.getRect().right;
                    int b = child.getRect().bottom;
                    child.layout(l, t, r, b);
                }
            }
            childs.put(position, child);
        }
    }

    @Override
    public void setSelection(int position) {

    }

    @Override
    public AbstractCapsulesAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(AbstractCapsulesAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View getSelectedView() {
        return null;
    }
}
