package cat.alorma.capsules.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.abs.EmptyCapsule;
import cat.alorma.capsulecorp.library.capsule.impl.ColorCapsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;
import cat.alorma.capsulecorp.library.capsule.impl.UrlBitmapCapsule;
import cat.alorma.capsulecorp.library.type.Type;
import cat.alorma.capsules.R;
import cat.alorma.capsules.ui.capsules.AQueryCapsule;
import cat.alorma.capsules.ui.capsules.CustomTypeFive;
import cat.alorma.capsules.ui.capsules.PicassoCapsule;

public class AnimFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_main_anim, null);
    }

    @Override
    protected void changeView(int progress) {
        progress++;
        Log.e("PROGRESS", " " + progress);
        /*((DispenserAnimatedView)dispenserView).setSeg(progress);*/
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Capsule c1 =  new TextCapsule("1", Color.parseColor("#000000"), Color.parseColor("#e67e22"));
        Capsule c2 = new EmptyCapsule();
        Capsule c3 = new TextCapsule("4", Color.parseColor("#000000"), Color.parseColor("#1abc9c"));
        Capsule c4 = new ColorCapsule(Color.parseColor("#e67e22"));
        Capsule c5 = new ColorCapsule(Color.parseColor("#9b59b6"));
        Capsule c6 = new ColorCapsule(Color.parseColor("#bdc3c7"));
        Capsule c7 = new ColorCapsule(Color.parseColor("#FF0000"));
        Capsule c8 = new PicassoCapsule(getActivity(), "http://pandalabs.pandasecurity.com/es/wp-content/uploads/2010/03/Mariposaimage.jpg");
        Capsule c9 = new UrlBitmapCapsule(getActivity(), "http://upload.wikimedia.org/wikipedia/commons/5/56/Candy-Corn.jpg");
        Capsule c10 = new AQueryCapsule(getActivity(), "http://6269-9001.zippykid.netdna-cdn.com/wp-content/uploads/2013/09/Dog-Computer-Wallpaper.jpg");
        Capsule c11 = new PicassoCapsule(getActivity(), "http://www.clker.com/cliparts/h/e/A/t/U/Z/red-star-hi.png");
        Capsule c12 = new TextCapsule("1", Color.parseColor("#000000"), Color.parseColor("#e67e22"));
        Capsule c13 = new TextCapsule("2", Color.parseColor("#000000"), Color.parseColor("#9b59b6"));
        Capsule c14 = new TextCapsule("3", Color.parseColor("#000000"), Color.parseColor("#bdc3c7"));
        Capsule c15 = new TextCapsule("4", Color.parseColor("#000000"), Color.parseColor("#1abc9c"));


        /*dispenserView.addCapsule(c1);
        dispenserView.addCapsule(c2);
        dispenserView.addCapsule(c3);
        dispenserView.addCapsule(c4);
        dispenserView.addCapsule(c5);
        dispenserView.addCapsule(c6);
        dispenserView.addCapsule(c7);
        dispenserView.addCapsule(c8);
        dispenserView.addCapsule(c9);
        dispenserView.addCapsule(c10);
        dispenserView.addCapsule(c11);
        dispenserView.addCapsule(c12);
        dispenserView.addCapsule(c13);
        dispenserView.addCapsule(c14);
        dispenserView.addCapsule(c15);*/

    }

    @Override
    protected Capsule getCapsule1() {
        return null;
    }

    @Override
    protected Capsule getCapsule2() {
        return null;
    }

    @Override
    protected Capsule getCapsule3() {
        return null;
    }

    @Override
    protected Capsule getCapsule4() {
        return null;
    }

    @Override
    protected Type getType() {
        return new CustomTypeFive();
    }

    @Override
    public String getTitle() {
        return "Animation";
    }
}
