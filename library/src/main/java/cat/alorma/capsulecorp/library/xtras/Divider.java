package cat.alorma.capsulecorp.library.xtras;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import cat.alorma.capsulecorp.library.R;
import cat.alorma.capsulecorp.library.Utils;

/**
 * Created by Bernat on 17/02/14.
 */
public class Divider implements Drawer{

    public int color;
    public int size;

    public Divider() {
        this(-1, -1);
    }

    public Divider(int color) {
        this(color, -1);
    }

    public Divider(Context context, TypedArray a) {
        color = a.getInt(R.styleable.dispenserattrs_divider_color, -1);
        if (color == -1) {
            color = Color.parseColor("#EAEAEA");
        }
        size = Utils.dividerSize(context, a);
    }

    public Divider(int color, int size) {
        this.color = color;
        this.size = size;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void draw(Canvas canvas, Rect bounds) {
        if (size > 0 || color != -1) {
            Paint dividerPaint = new Paint();
            dividerPaint.setStyle(Paint.Style.FILL);
            dividerPaint.setColor(color);
            canvas.drawRect(bounds, dividerPaint);
        }
    }
}
