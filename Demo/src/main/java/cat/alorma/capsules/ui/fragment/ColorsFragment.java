package cat.alorma.capsules.ui.fragment;

import android.graphics.Color;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.ColorCapsule;

/**
 * Created by Bernat on 25/11/13.
 */
public class ColorsFragment extends BaseFragment {

    @Override
    protected Capsule getCapsule1() {
        return new ColorCapsule(Color.parseColor("#e67e22"));
    }

    @Override
    protected Capsule getCapsule2() {
        return new ColorCapsule(Color.parseColor("#9b59b6"));
    }

    @Override
    protected Capsule getCapsule3() {
        return new ColorCapsule(Color.parseColor("#bdc3c7"));
    }

    @Override
    protected Capsule getCapsule4() {
        return new ColorCapsule(Color.parseColor("#FF0000"));
    }
}
