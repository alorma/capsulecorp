package cat.alorma.capsules.ui.capsules;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;

import cat.alorma.capsulecorp.library.capsule.abs.ImageUrlCapsule;

/**
 * Created by Bernat on 29/11/13.
 */
public class AQueryCapsule extends ImageUrlCapsule {
    private Context ctx;

    public AQueryCapsule(Context ctx, String url) {
        super(url);
        this.ctx = ctx;
    }

    @Override
    protected void executeAsync(String url) {
        AQuery aq = new AQuery(ctx);
        aq.id(new ImageView(ctx));
        aq.image(url, true, true, 0, 0, new BitmapAjaxCallback() {

            @Override
            public void callback(String url, ImageView iv, Bitmap bm, AjaxStatus status) {
                setDownloadedBitmap(bm);
            }

        });
    }
}
