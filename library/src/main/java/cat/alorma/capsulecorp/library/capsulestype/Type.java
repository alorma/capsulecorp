package cat.alorma.capsulecorp.library.capsulestype;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.SparseArray;

import java.util.ArrayList;

import cat.alorma.capsulecorp.library.DispenserView;

/**
 * Created by adria on 6/02/14.
 */
public class Type {

    private Rect[] rects;

    public Type(Rect[] rects){
        this.rects = rects;
    }

    public static ArrayList<Type> getInstance(DispenserView dispenserView){
        ArrayList<Type> types = new ArrayList<Type>();

        Rect clipBounds = new Rect(
                dispenserView.getLeft(),
                dispenserView.getTop(),
                dispenserView.getRight(),
                dispenserView.getBottom());

        int paddingLeft = dispenserView.getPaddingLeft() < clipBounds.width() / 2 ? dispenserView.getPaddingLeft() : 0;
        int paddingRight = dispenserView.getPaddingRight() < clipBounds.width() / 2 ? dispenserView.getPaddingRight() : 0;
        int paddingTop = dispenserView.getPaddingTop() < clipBounds.height() / 2 ? dispenserView.getPaddingTop() : 0;
        int paddingBottom = dispenserView.getPaddingBottom() < clipBounds.height() / 2 ? dispenserView.getPaddingBottom() : 0;

        clipBounds.left = paddingLeft;
        clipBounds.right = clipBounds.right - paddingRight;
        clipBounds.top = paddingTop;
        clipBounds.bottom = clipBounds.bottom - paddingBottom;

        Rect paddings = new Rect(paddingLeft,paddingTop,paddingRight,paddingBottom);

        types.add(TypeOne(clipBounds));
        types.add(TypeTwe(clipBounds, paddings));
        types.add(TypeTree(clipBounds, paddings));
        types.add(TypeFour(clipBounds, paddings));

        return types;
    }

    private static Type TypeOne(Rect clipBounds){
        return new Type(new Rect[]{clipBounds});
    }
    private static Type TypeTwe(Rect clipBounds,Rect paddings){

        int centerX = (clipBounds.width() / 2) + paddings.left;

        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centerX, clipBounds.bottom);
        Rect rect2 = new Rect(centerX, clipBounds.top, clipBounds.right, clipBounds.bottom);

        return new Type(new Rect[]{rect1,rect2});
    }
    private static Type TypeTree(Rect clipBounds,Rect paddings){

        int centerX = (clipBounds.width() / 2) + paddings.left;
        int centerY = (clipBounds.height() / 2) + paddings.top;

        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centerX, clipBounds.bottom);
        Rect rect2 = new Rect(centerX, clipBounds.top, clipBounds.right, centerY);
        Rect rect3 = new Rect(centerX, centerY, clipBounds.right, clipBounds.bottom);

        return new Type(new Rect[]{rect1,rect2,rect3});
    }
    private static Type TypeFour(Rect clipBounds,Rect paddings){

        int centerX = (clipBounds.width() / 2) + paddings.left;
        int centerY = (clipBounds.height() / 2) + paddings.top;

        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, centerX, centerY);
        Rect rect2 = new Rect(centerX, clipBounds.top, clipBounds.right, centerY);
        Rect rect3 = new Rect(clipBounds.left, centerY, centerX, clipBounds.bottom);
        Rect rect4 = new Rect(centerX, centerY, clipBounds.right, clipBounds.bottom);

        return new Type(new Rect[]{rect1,rect2,rect3,rect4});
    }


    public Rect[] getRects() {
        return rects;
    }

    public void setRects(Rect[] rects) {
        this.rects = rects;
    }
}
