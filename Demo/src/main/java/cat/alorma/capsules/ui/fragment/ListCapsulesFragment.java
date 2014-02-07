package cat.alorma.capsules.ui.fragment;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.impl.ColorCapsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;
import cat.alorma.capsules.R;
import cat.alorma.capsules.model.Data;
import cat.alorma.capsules.ui.adapter.CapsulesAdapter;
import cat.alorma.capsules.ui.capsules.PicassoCapsule;

/**
 * Created by Bernat on 25/11/13.
 */
public class ListCapsulesFragment extends ListFragment implements AbsListView.OnScrollListener {
    private CapsulesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getListView() != null) {
            getListView().setOnScrollListener(this);
        }

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

        adapter = new CapsulesAdapter(getActivity(), datas);

        setListAdapter(adapter);
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
        capsules.add(new ColorCapsule(Color.parseColor("#FF00FF")));
        capsules.add(new TextCapsule("Helena", Color.parseColor("#000000"), Color.parseColor("#bdc3c7")));
        capsules.add(new PicassoCapsule(getActivity(), "http://pandalabs.pandasecurity.com/es/wp-content/uploads/2010/03/Mariposaimage.jpg"));
        return capsules;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        adapter.setFling(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_FLING);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.action_mask) {
            adapter.setMaskEnabled();
        }

        return true;
    }
}
