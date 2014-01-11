package cat.alorma.capsulecorp.library.capsule.impl;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Bernat on 25/11/13.
 */
public class TextCapsule extends ColorCapsule {

    private String text;
    private int textColor;

    public TextCapsule(String text, int textColor, int backgroundColor) {
        super(backgroundColor);
        this.text = text;
        this.textColor = textColor;
    }

    @Override
    public void boom(Canvas canvas, Paint paint, Rect rect) {
        super.boom(canvas, paint, rect);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.CENTER);

        int width90 = (rect.width() * 90) / 100;
        int height90 = (rect.height() * 90) / 100;

        int textSize = (width90 + height90) / 4;

        paint.setTextSize(textSize);

        int xPos =  (rect.left + rect.right)  / 2;
        int yPos = rect.top + (rect.height()) / 2;

        float textWidth = paint.measureText(text, 0, 1);

        yPos = (int) (yPos + textWidth / 2);

        canvas.drawText(text.substring(0, 1), xPos, yPos, paint);
    }
}
