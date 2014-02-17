package cat.alorma.capsulecorp.library.xtras;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;

import cat.alorma.capsulecorp.library.R;

/**
 * Created by Bernat on 17/02/14.
 */
public class Mask implements Drawer{

    private int maskResource;
    private int maskBackgroundResource;
    private boolean enabled;
    private boolean backgroundEnabled;

    public Mask() {
        this(-1, -1);
    }

    public Mask(int maskResource) {
        this(maskResource, -1);
    }

    public Mask(TypedArray a) {
        this(a.getResourceId(R.styleable.dispenserattrs_mask, -1));
    }

    public Mask(int maskResource, int maskBackgroundResource) {
        this.maskResource = maskResource;
        this.maskBackgroundResource = maskBackgroundResource;

        this.enabled = maskResource != -1;
        this.backgroundEnabled = maskBackgroundResource != -1;
    }

    public int getMaskResource() {
        return maskResource;
    }

    public void setMaskResource(int maskResource) {
        this.maskResource = maskResource;
    }

    public int getMaskBackgroundResource() {
        return maskBackgroundResource;
    }

    public void setMaskBackgroundResource(int maskBackgroundResource) {
        this.maskBackgroundResource = maskBackgroundResource;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isBackgroundEnabled() {
        return backgroundEnabled;
    }

    public void setBackgroundEnabled(boolean backgroundEnabled) {
        this.backgroundEnabled = backgroundEnabled;
    }

    @Override
    public void draw(Canvas canvas, Rect bounds) {

    }
}
