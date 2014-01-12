package cat.alorma.capsulecorp.library.capsule.impl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import cat.alorma.capsulecorp.library.capsule.abs.BitmapCapsule;

/**
 * Created by Bernat on 25/11/13.
 */
public class DrawableCapsule extends BitmapCapsule {

    private final Resources resources;
    private int drawableId;
    public DrawableCapsule(Context ctx, int resourceId) {
        super();
        this.resources = ctx.getResources();
        this.drawableId = resourceId;
    }

    @Override
    protected Bitmap getBitmap() {
        return BitmapFactory.decodeResource(resources, drawableId);
    }

}
