package cat.alorma.capsules.ui.fragment;

import android.graphics.Color;

import cat.alorma.capsulecorp.library.capsule.impl.ColorCapsule;

/**
 * Created by Bernat on 25/11/13.
 */
public class ColorsFragment extends BaseFragment {
    @Override
    protected void generateCapsules() {
        setCapsule1(new ColorCapsule(Color.parseColor("#e67e22")));
        setCapsule2(new ColorCapsule(Color.parseColor("#9b59b6")));
        setCapsule3(new ColorCapsule(Color.parseColor("#bdc3c7")));
        setCapsule4(new ColorCapsule(Color.parseColor("#1abc9c")));
    }
}
