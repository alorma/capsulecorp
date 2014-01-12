package cat.alorma.capsulecorp.library.capsule.abs;

import android.graphics.Bitmap;

/**
 * Created by Bernat on 25/11/13.
 */
public abstract class ImageUrlCapsule extends BitmapCapsule {

    private String url;
    private Bitmap downloadedBitmap;

    public ImageUrlCapsule(String url) {
        super();
        this.url = url;
    }

    @Override
    protected Bitmap getBitmap() {
        if (downloadedBitmap == null) {
            executeAsync(url);
        }
        return downloadedBitmap;
    }

    protected abstract void executeAsync(String url);

    public void setDownloadedBitmap(Bitmap downloadedBitmap) {
        this.downloadedBitmap = downloadedBitmap;

        if (getCapsuleListener() != null) {
            getCapsuleListener().invalidateParent();
        }
    }
}
