package cat.alorma.capsules.ui.capsules;

import android.graphics.Rect;
import android.util.Log;

import cat.alorma.capsulecorp.library.type.Type;

/**
 * Created by adria on 8/02/14.
 */
public class CustomTypeFive extends Type {
    public CustomTypeFive(){
        super();
    }

    public CustomTypeFive(Rect[] rects){
        super(rects);
    }

    @Override
    public Type calculateRects(Rect clipBounds, Rect centers, Rect paddings) {
        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centers.left, centers.top);
        Rect rect2 = new Rect(centers.right, clipBounds.top, clipBounds.right, centers.top);
        Rect rect3 = new Rect(clipBounds.left, centers.bottom, centers.left, clipBounds.bottom);
        Rect rect4 = new Rect(centers.right, centers.bottom, clipBounds.right, clipBounds.bottom);

        int w = clipBounds.width();//clipBounds.right + paddings.left;
        int h = clipBounds.height();//clipBounds.bottom + paddings.top;
        Rect rect5 = new Rect(
                w/4 + paddings.left,
                h/4 + paddings.top,
                w/4*3 + paddings.right,
                h/4*3 + paddings.bottom);

        this.setRects(new Rect[]{rect1, rect2, rect3, rect4, rect5});
        return this;
    }
}
