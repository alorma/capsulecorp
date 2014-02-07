package cat.alorma.capsulecorp.library.type;

import android.graphics.Rect;
import android.view.View;

/**
 * Created by Bernat on 7/02/14.
 */
public class TypeFactory {
    private static int paddingLeft;
    private static int paddingRight;
    private static int paddingTop;
    private static int paddingBottom;
    private static int centerXL;
    private static int centerXR;
    private static int centerYT;
    private static int centerYB;
    private static Rect clipBounds;

    public static Type newInstance(View view, int size, int dividerSize) {
        calculateClipBounds(view);

        calculatePaddings(view);
        calculateCenters(dividerSize);

        Type type = createType(size);

        return type;
    }

    private static void calculateClipBounds(View view) {
        clipBounds =  new Rect(
                0,
                0,
                view.getWidth(),
                view.getHeight());
    }

    private static void calculatePaddings(View view) {
        paddingLeft = view.getPaddingLeft() < clipBounds.width() / 2 ? view.getPaddingLeft() : 0;
        paddingRight = view.getPaddingRight() < clipBounds.width() / 2 ? view.getPaddingRight() : 0;
        paddingTop = view.getPaddingTop() < clipBounds.height() / 2 ? view.getPaddingTop() : 0;
        paddingBottom = view.getPaddingBottom() < clipBounds.height() / 2 ? view.getPaddingBottom() : 0;

        clipBounds.left = paddingLeft;
        clipBounds.right = clipBounds.right - paddingRight;
        clipBounds.top = paddingTop;
        clipBounds.bottom = clipBounds.bottom - paddingBottom;
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

        type.setClipBounds(clipBounds);

        int[] centers = new int[] {centerXL, centerYT, centerXR, centerYB};

        type.setCenters(centers);

        return type;
    }

    private static void calculateCenters(int dividerSize) {
        int centerX = (clipBounds.width() / 2) + paddingLeft;
        int centerY = (clipBounds.height() / 2) + paddingTop;

        centerXL = centerX;
        centerXR = centerX;
        centerYT = centerY;
        centerYB = centerY;

        if (dividerSize != -1) {
            centerXL = centerXL - (dividerSize / 2);
            centerXR = centerXR + (dividerSize / 2);
            centerYT = centerYT - (dividerSize / 2);
            centerYB = centerYB + (dividerSize / 2);
        }
    }
}
