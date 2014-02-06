package cat.alorma.capsulecorp.library;

import android.content.Context;
import android.content.res.TypedArray;

/**
 * Created by Bernat on 6/02/14.
 */
public class Utils {

    public static int dividerSize(Context ctx, TypedArray a) {
        int widthDim = dimenValue(a, R.styleable.dispenserattrs_divider_size);
        int widthInt = intValue(a, R.styleable.dispenserattrs_divider_size);
        int widthResource = resourceValue(a, R.styleable.dispenserattrs_divider_size);

        if (widthDim != -1) {
            return widthDim;
        } else if (widthInt != -1) {
            return widthInt;
        }else if (widthResource != -1) {
            return widthResource;
        }

        return 0;
    }

    private static int intValue(TypedArray a, int resource) {
        int i = -1;
        try {
            i = a.getInt(resource, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    private static int dimenValue(TypedArray a, int resource) {
        int i = -1;
        try {
            i = a.getDimensionPixelOffset(resource, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    private static int resourceValue(TypedArray a, int resource) {
        int i = -1;
        try {
            i = a.getResourceId(resource, -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

}
