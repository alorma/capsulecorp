package cat.alorma.capsulecorp.library.distributor;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.List;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.type.Type;
import cat.alorma.capsulecorp.library.xtras.Divider;
import cat.alorma.capsulecorp.library.xtras.Mask;

/**
 * Created by Bernat on 17/02/14.
 */
public class Distributor {

    private List<Capsule> capsules;
    private Type type;
    private Mask mask;
    private Divider divider;

    public Distributor(List<Capsule> capsules) {
        this(capsules, null, null, null);
    }

    public Distributor(List<Capsule> capsules, Type type) {
        this(capsules, type, null, null);
    }

    public Distributor(List<Capsule> capsules, Type type, Mask mask) {
        this(capsules, type, mask, null);
    }

    public Distributor(List<Capsule> capsules, Type type, Mask mask, Divider divider) {
        this.capsules = capsules;
        this.type = type;
        this.mask = mask;
        this.divider = divider;
    }

    public List<Capsule> getCapsules() {
        return capsules;
    }

    public void setCapsules(List<Capsule> capsules) {
        this.capsules = capsules;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Mask getMask() {
        return mask;
    }

    public void setMask(Mask mask) {
        this.mask = mask;
    }

    public Divider getDivider() {
        return divider;
    }

    public void setDivider(Divider divider) {
        this.divider = divider;
    }

    public void drawDividers(Canvas canvas, Rect bounds) {
        if (divider != null) {
            divider.draw(canvas, bounds);
        }
    }

    public void draw(Canvas canvas, Paint paint) {
        if (capsules != null && size() > 0 && type.getRects() != null && type.getRects().length > 0) {
            for (int i = 0; i < size(); i++) {
                Capsule capsule = capsules.get(i);
                drawCapsule(canvas, capsule, paint, type.getRects()[i]);
            }
        }
    }

    private void drawCapsule(Canvas canvas, Capsule capsule, Paint paint, Rect rect) {
        if (canvas != null && capsule != null && rect != null) {
            capsule.boom(canvas, paint, rect);
        }
    }

    private int size() {
        int size = capsules.size();
        size = size <= type.size() ? size : type.size();
        return size;
    }
}
