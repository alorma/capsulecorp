package cat.alorma.capsulecorp.library.distributor;

import android.content.Context;
import android.graphics.Rect;

/**
 * Created by Bernat on 11/03/14.
 */
public class CapsulesAdapter extends AbstractCapsulesAdapter {

    public CapsulesAdapter(Context context) {
        super(context);
    }

    @Override
    protected Rect getRect(int position, Rect paintRect, int paddingSize, int capsulesSize) {
        Rect rect = paintRect;

        switch (capsulesSize) {
            case 1:
                rect = paintRect;
                break;
            case 2:
                rect = calculateTwoRects(paintRect, position);
                break;
        }
        return rect;
    }

    private Rect calculateTwoRects(Rect parentRect, int position) {
        Rect rect = parentRect;
        switch (position) {
            case 0:
                rect = new Rect(parentRect.left, parentRect.top, (parentRect.left + parentRect.right) / 2, parentRect.bottom);
                break;
            case 1:
                rect = new Rect((parentRect.left + parentRect.right) / 2, parentRect.top, parentRect.right, parentRect.bottom);
                break;
        }
        return rect;
    }
}
