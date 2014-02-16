package cat.alorma.capsulecorp.library.capsule.impl;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;

import java.io.BufferedInputStream;
import java.io.InputStream;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;

/**
 * Created by Bernat on 16/02/14.
 */
public class ContactCapsule extends Capsule {

    private static String DISPLAY_NAME = Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.HONEYCOMB ?
            ContactsContract.Data.DISPLAY_NAME_PRIMARY :
            ContactsContract.Data.DISPLAY_NAME;

    private static final String SELECTION = ContactsContract.Data.LOOKUP_KEY + " = ?";
    private static final String[] PROJECTION = new String[]{
            ContactsContract.Data._ID,
            DISPLAY_NAME,
    };

    private Context context;
    private String lookup;
    private Uri uri;
    private int textColor;
    private int background;

    public ContactCapsule(Context context, String lookup, Uri uri, int textColor, int background) {
        this.context = context;
        this.lookup = lookup;
        this.uri = uri;
        this.textColor = textColor;
        this.background = background;
    }
    Bitmap image = null;
    @Override
    public void create(Canvas canvas, Paint paint, Rect rect) {
        ContentResolver contentResolver = context.getContentResolver();
        if(image == null){
            InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver, uri);

            if (inputStream != null) {
                BufferedInputStream buf = new BufferedInputStream(inputStream);
                image = BitmapFactory.decodeStream(buf);

                if (image != null && paint != null && rect != null) {
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawBitmap(image, null, rect, paint);
                }

            } else {
                Cursor cursor = contentResolver.query(uri, PROJECTION, SELECTION, new String[]{lookup}, null);
                if (cursor != null && cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(DISPLAY_NAME);
                    if (index > 0) {
                        String name = cursor.getString(index);

                        if (name != null) {
                            paint.setColor(background);
                            paint.setStyle(Paint.Style.FILL);
                            canvas.drawRect(rect, paint);

                            name = name.toUpperCase();

                            paint.setStyle(Paint.Style.FILL);
                            paint.setColor(textColor);
                            paint.setTextAlign(Paint.Align.CENTER);

                            int width90 = (rect.width() * 90) / 100;
                            int height90 = (rect.height() * 90) / 100;

                            int textSize = (width90 + height90) / 4;

                            paint.setTextSize(textSize);

                            int xPos = (rect.left + rect.right) / 2;
                            int yPos = rect.top + (rect.height()) / 2;

                            float textWidth = paint.measureText(name, 0, 1);

                            yPos = (int) (yPos + textWidth / 2);

                            canvas.drawText(name.substring(0, 1), xPos, yPos, paint);
                        }
                    }
                }
                cursor.close();
            }
        }else{
            paint.setStyle(Paint.Style.FILL);
            canvas.drawBitmap(image, null, rect, paint);
        }
    }
}
