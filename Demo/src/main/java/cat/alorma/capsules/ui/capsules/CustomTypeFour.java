package cat.alorma.capsules.ui.capsules;

import android.graphics.Rect;

import cat.alorma.capsulecorp.library.type.Type;

/**
 * Created by adria on 8/02/14.
 */
public class CustomTypeFour extends Type {
    public CustomTypeFour(){
        super();
    }

    public CustomTypeFour(Rect[] rects){
        super(rects);
    }

    @Override
    public Type calculateRects(Rect clipBounds, Rect centers, Rect paddings) {
        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centers.left, centers.top);
        Rect rect2 = new Rect(centers.right, clipBounds.top, clipBounds.right, centers.top);
        Rect rect3 = new Rect(clipBounds.left, centers.bottom, centers.left, clipBounds.bottom);
        Rect rect4 = new Rect(centers.right, centers.bottom, clipBounds.right, clipBounds.bottom);

        this.setRects(new Rect[]{rect1, rect2, rect3, rect4});
        return this;
    }
}
