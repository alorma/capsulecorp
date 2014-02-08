package cat.alorma.capsules.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.ColorCapsule;
import cat.alorma.capsulecorp.library.type.Type;
import cat.alorma.capsules.ui.capsules.CustomType;

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

    @Override
    protected Type getType() {
        return null;
    }
}
