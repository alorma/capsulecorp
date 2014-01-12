package cat.alorma.capsules.ui.fragment;

import android.graphics.Color;

import cat.alorma.capsulecorp.library.capsule.impl.ColorCapsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;

/**
 * Created by Bernat on 25/11/13.
 */
public class TextColorsFragment extends BaseFragment {
    @Override
    protected void generateCapsules() {
        setCapsule1(new TextCapsule("0", Color.parseColor("#000000"), Color.parseColor("#e67e22")));
        setCapsule2(new TextCapsule("1", Color.parseColor("#000000"), Color.parseColor("#9b59b6")));
        setCapsule3(new TextCapsule("2", Color.parseColor("#000000"), Color.parseColor("#bdc3c7")));
        setCapsule4(new TextCapsule("3", Color.parseColor("#000000"), Color.parseColor("#1abc9c")));
    }
}
