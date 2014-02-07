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
                0,
                0,
                dispenserView.getWidth(),
                dispenserView.getHeight());

        int paddingLeft = dispenserView.getPaddingLeft() < clipBounds.width() / 2 ? dispenserView.getPaddingLeft() : 0;
        int paddingRight = dispenserView.getPaddingRight() < clipBounds.width() / 2 ? dispenserView.getPaddingRight() : 0;
        int paddingTop = dispenserView.getPaddingTop() < clipBounds.height() / 2 ? dispenserView.getPaddingTop() : 0;
        int paddingBottom = dispenserView.getPaddingBottom() < clipBounds.height() / 2 ? dispenserView.getPaddingBottom() : 0;

        clipBounds.left = paddingLeft;
        clipBounds.right = clipBounds.right - paddingRight;
        clipBounds.top = paddingTop;
        clipBounds.bottom = clipBounds.bottom - paddingBottom;

        Rect paddings = new Rect(paddingLeft,paddingTop,paddingRight,paddingBottom);
     //   Rect paddings = new Rect(0,0,0,0);

        int centerX = (clipBounds.width() / 2) + paddingLeft;
        int centerY = (clipBounds.height() / 2) + paddingTop;

        int centerXL = centerX;
        int centerXR = centerX;
        int centerYT = centerY;
        int centerYB = centerY;

        if (dispenserView.getDivider_size() != -1) {
            centerXL = centerXL - (dispenserView.getDivider_size() / 2);
            centerXR = centerXR + (dispenserView.getDivider_size() / 2);
            centerYT = centerYT - (dispenserView.getDivider_size() / 2);
            centerYB = centerYB + (dispenserView.getDivider_size()/ 2);
        }

        types.add(TypeOne(clipBounds));
        types.add(TypeTwe(clipBounds, paddings,new int[]{centerXL,centerXR,centerYT,centerYB}));
        types.add(TypeTree(clipBounds, paddings,new int[]{centerXL,centerXR,centerYT,centerYB}));
        types.add(TypeFour(clipBounds, paddings,new int[]{centerXL,centerXR,centerYT,centerYB}));

        return types;
    }

    private static Type TypeOne(Rect clipBounds){
        return new Type(new Rect[]{clipBounds});
    }
    private static Type TypeTwe(Rect clipBounds,Rect paddings, int[] dividers){

        int centerX = (clipBounds.width() / 2) + paddings.left;

        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, dividers[0], clipBounds.bottom);
        Rect rect2 = new Rect(dividers[1], clipBounds.top, clipBounds.right, clipBounds.bottom);

        return new Type(new Rect[]{rect1,rect2});
    }
    private static Type TypeTree(Rect clipBounds,Rect paddings, int[] dividers){

        int centerX = (clipBounds.width() / 2) + paddings.left;
        int centerY = (clipBounds.height() / 2) + paddings.top;

        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, dividers[0], clipBounds.bottom);
        Rect rect2 = new Rect(dividers[1], clipBounds.top, clipBounds.right, dividers[2]);
        Rect rect3 = new Rect(dividers[1], dividers[3], clipBounds.right, clipBounds.bottom);

        return new Type(new Rect[]{rect1,rect2,rect3});
    }
    private static Type TypeFour(Rect clipBounds,Rect paddings, int[] dividers){

        int centerX = (clipBounds.width() / 2) + paddings.left;
        int centerY = (clipBounds.height() / 2) + paddings.top;

        Rect rect1 = new Rect(clipBounds.left, clipBounds.top, dividers[0], dividers[2]);
        Rect rect2 = new Rect(dividers[1], clipBounds.top, clipBounds.right, dividers[2]);
        Rect rect3 = new Rect(clipBounds.left, dividers[3], dividers[0], clipBounds.bottom);
        Rect rect4 = new Rect(dividers[1], dividers[3], clipBounds.right, clipBounds.bottom);

        return new Type(new Rect[]{rect1,rect2,rect3,rect4});
    }


    public Rect[] getRects() {
        return rects;
    }

    public void setRects(Rect[] rects) {
        this.rects = rects;
    }
}
