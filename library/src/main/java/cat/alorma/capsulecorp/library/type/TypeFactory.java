package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 * Created by Bernat on 7/02/14.
 */
public class TypeFactory {
    private static Rect clipBounds;
    private static Rect centers;
    private static Rect paddings;

    public static Type newInstance(View view, int size, int dividerSize) {
        Type type = createType(size);
        calculateClipBounds(view);
        calculatePaddings(view);
        calculateCenters(dividerSize);

        return setData(view, type, dividerSize);
    }

    public static Type setData(View view, Type type, int dividerSize){
        calculateClipBounds(view);
        calculatePaddings(view);
        calculateCenters(dividerSize);
        return type.calculateRects(clipBounds,centers,paddings);
    }

    private static void calculateClipBounds(View view) {
        clipBounds = new Rect(0, 0, view.getWidth(), view.getHeight());
    }

    private static void calculatePaddings(View view) {
        int paddingLeft = view.getPaddingLeft() < clipBounds.width() / 2 ? view.getPaddingLeft() : 0;
        int paddingRight = view.getPaddingRight() < clipBounds.width() / 2 ? view.getPaddingRight() : 0;
        int paddingTop = view.getPaddingTop() < clipBounds.height() / 2 ? view.getPaddingTop() : 0;
        int paddingBottom = view.getPaddingBottom() < clipBounds.height() / 2 ? view.getPaddingBottom() : 0;

        clipBounds.left = paddingLeft;
        clipBounds.right = clipBounds.right - paddingRight;
        clipBounds.top = paddingTop;
        clipBounds.bottom = clipBounds.bottom - paddingBottom;

        paddings = new Rect(paddingLeft,paddingTop,paddingRight,paddingBottom);
    }

    private static Type createType(int size) {
        Type type = new TypeOne();
        switch (size) {
            case 2:
                type = new TypeTwo();
                break;
            case 3:
                type = new TypeThree();
                break;
            case 4:
                type = new TypeFour();
                break;
        }
        return type;
    }

    private static void calculateCenters(int dividerSize) {
        int centerX = (clipBounds.width() / 2) + paddings.left;
        int centerY = (clipBounds.height() / 2) + paddings.top;

        int centerXL = centerX;
        int centerXR = centerX;
        int centerYT = centerY;
        int centerYB = centerY;

        if (dividerSize != -1) {
            centerXL = centerXL - (dividerSize / 2);
            centerXR = centerXR + (dividerSize / 2);
            centerYT = centerYT - (dividerSize / 2);
            centerYB = centerYB + (dividerSize / 2);
        }

        centers = new Rect(centerXL,centerYT,centerXR,centerYB);
    }
}
