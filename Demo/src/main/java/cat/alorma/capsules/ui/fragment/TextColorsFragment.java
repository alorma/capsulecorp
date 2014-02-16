package cat.alorma.capsules.ui.fragment;

import android.graphics.Color;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.abs.EmptyCapsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;
import cat.alorma.capsulecorp.library.type.Type;

/**
 * Created by Bernat on 25/11/13.
 */
public class TextColorsFragment extends BaseFragment {
    @Override
    protected Capsule getCapsule1() {
        return new TextCapsule("1", Color.parseColor("#000000"), Color.parseColor("#e67e22"));
    }

    @Override
    protected Capsule getCapsule2() {
        return new TextCapsule("2", Color.parseColor("#000000"), Color.parseColor("#9b59b6"));
    }

    @Override
    protected Capsule getCapsule3() {
        return new TextCapsule("3", Color.parseColor("#000000"), Color.parseColor("#bdc3c7"));
    }

    @Override
    protected Capsule getCapsule4() {
        return new TextCapsule("4", Color.parseColor("#000000"), Color.parseColor("#1abc9c"));
    }

    @Override
    protected Type getType() {
        return null;
    }

    @Override
    public String getTitle() {
        return "Texts";
    }
}
