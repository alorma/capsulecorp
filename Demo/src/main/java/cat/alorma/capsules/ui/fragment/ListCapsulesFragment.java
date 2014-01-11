package cat.alorma.capsules.ui.fragment;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.ColorCapsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;
import cat.alorma.capsules.model.Data;
import cat.alorma.capsules.ui.adapter.CapsulesAdapter;
import cat.alorma.capsules.ui.capsules.PicassoCapsule;

/**
 * Created by Bernat on 25/11/13.
 */
public class ListCapsulesFragment extends ListFragment {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<Data> datas = new ArrayList<Data>();
        for (int i = 0; i < 100; i++) {
            Data data = new Data();
            data.setName("Element " + i);

            if ((i % 2) == 0) {
                data.setCapsules(get1Capsules());
            } else if ((i % 3) == 0) {
                data.setCapsules(get3Capsules());
            } else if ((i % 5) == 0) {
                data.setCapsules(get4Capsules());
            } else {
                data.setCapsules(get2Capsules());
            }
            datas.add(data);
        }

        CapsulesAdapter adapter = new CapsulesAdapter(getActivity(), datas);

        setListAdapter(adapter);
    }

    private List<Capsule> getCapsules() {
        Random random = new Random();

        switch (random.nextInt(5)) {
            case 0:
            case 1:
                return get1Capsules();
            case 2:
                return get2Capsules();
            case 3:
                return get3Capsules();
            case 4:
                return get4Capsules();
        }
        return null;
    }

    private List<Capsule> get1Capsules() {
        List<Capsule> capsules = new ArrayList<Capsule>();
        capsules.add(new TextCapsule("Helena", Color.parseColor("#000000"), Color.parseColor("#bdc3c7")));
        return capsules;
    }

    private List<Capsule> get2Capsules() {
        List<Capsule> capsules = new ArrayList<Capsule>();
        capsules.add(new PicassoCapsule(getActivity(), "http://1.bp.blogspot.com/-1uQRYMklACU/ToQ6aL-5uUI/AAAAAAAAAgQ/9_u0922cL14/s640/cute-puppy-dog-wallpapers.jpg"));
        capsules.add(new TextCapsule("Helena", Color.parseColor("#000000"), Color.parseColor("#bda3c7")));
        return capsules;
    }

    private List<Capsule> get3Capsules() {
        List<Capsule> capsules = new ArrayList<Capsule>();
        capsules.add(new ColorCapsule(Color.parseColor("#e67e22")));
        capsules.add(new PicassoCapsule(getActivity(), "http://pandalabs.pandasecurity.com/es/wp-content/uploads/2010/03/Mariposaimage.jpg"));
        capsules.add(new TextCapsule("Leandro", Color.parseColor("#000000"), Color.parseColor("#1abc9c")));
        return capsules;
    }

    private List<Capsule> get4Capsules() {
        List<Capsule> capsules = new ArrayList<Capsule>();
        capsules.add(new PicassoCapsule(getActivity(), "http://1.bp.blogspot.com/-1uQRYMklACU/ToQ6aL-5uUI/AAAAAAAAAgQ/9_u0922cL14/s640/cute-puppy-dog-wallpapers.jpg"));
        capsules.add(new PicassoCapsule(getActivity(), "http://1.bp.blogspot.com/_pdzHm9ymj4Q/TRDwQ9dvmII/AAAAAAAAChc/nkp49tkNVWA/s400/rose-flower.jpg"));
        capsules.add(new TextCapsule("Helena", Color.parseColor("#000000"), Color.parseColor("#bdc3c7")));
        capsules.add(new PicassoCapsule(getActivity(), "http://pandalabs.pandasecurity.com/es/wp-content/uploads/2010/03/Mariposaimage.jpg"));
        return capsules;
    }
}
