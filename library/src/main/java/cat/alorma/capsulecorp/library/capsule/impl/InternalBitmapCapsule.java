package cat.alorma.capsulecorp.library.capsule.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import cat.alorma.capsulecorp.library.capsule.abs.BitmapCapsule;

/**
 * Created by Bernat on 9/01/14.
 */
public class InternalBitmapCapsule extends BitmapCapsule {

    private String path;

    public InternalBitmapCapsule(String path) {
        this.path = path;
    }

    @Override
    protected Bitmap getBitmap() {
        return BitmapFactory.decodeFile(path);
    }
}
