package cat.alorma.capsules.ui.capsules;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import cat.alorma.capsulecorp.library.capsule.abs.ImageUrlCapsule;

/**
 * Created by Bernat on 29/11/13.
 */
public class PicassoCapsule extends ImageUrlCapsule {
    private Context ctx;

    public PicassoCapsule(Context ctx, String url) {
        super(url);
        this.ctx = ctx;
    }

    @Override
    protected void executeAsync(String url) {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                setDownloadedBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable drawable) {

            }

            @Override
            public void onPrepareLoad(Drawable drawable) {

            }
        };
        Picasso.with(ctx).load(url).into(target);
    }
}
