package cat.alorma.capsules.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.UrlBitmapCapsule;
import cat.alorma.capsulecorp.library.type.Type;
import cat.alorma.capsules.ui.capsules.AQueryCapsule;
import cat.alorma.capsules.ui.capsules.PicassoCapsule;

/**
 * Created by Bernat on 29/11/13.
 */
public class ImagesCapsulesFragment extends BaseFragment {

    private Capsule capsule1;
    private Capsule capsule2;
    private Capsule capsule3;
    private Capsule capsule4;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dispenserView.setDividerColor(Color.parseColor("#AEAEAE"));
    }

    @Override
    protected Capsule getCapsule1() {
        if (capsule1 == null) {
            capsule1 = new PicassoCapsule(getActivity(), "http://pandalabs.pandasecurity.com/es/wp-content/uploads/2010/03/Mariposaimage.jpg");
        }
        return capsule1;
    }

    @Override
    protected Capsule getCapsule2() {
        if (capsule2 == null) {
            capsule2 = new UrlBitmapCapsule(getActivity(), "http://upload.wikimedia.org/wikipedia/commons/5/56/Candy-Corn.jpg");
        }
        return capsule2;
    }

    @Override
    protected Capsule getCapsule3() {
        if (capsule3 == null) {
            capsule3 = new AQueryCapsule(getActivity(), "http://6269-9001.zippykid.netdna-cdn.com/wp-content/uploads/2013/09/Dog-Computer-Wallpaper.jpg");
        }
        return capsule3;
    }

    @Override
    protected Capsule getCapsule4() {
        if (capsule4 == null) {
            capsule4 = new PicassoCapsule(getActivity(), "http://www.clker.com/cliparts/h/e/A/t/U/Z/red-star-hi.png");
        }
        return capsule4;
    }

    @Override
    protected Type getType() {
        return null;
    }

    @Override
    public String getTitle() {
        return "Images";
    }
}
