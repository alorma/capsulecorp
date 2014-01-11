package cat.alorma.capsules.ui.fragment;

import cat.alorma.capsules.ui.capsules.PicassoCapsule;

/**
 * Created by Bernat on 29/11/13.
 */
public class ImagesCapsulesFragment extends BaseFragment {
    @Override
    protected void generateCapsules() {
        setCapsule1(new PicassoCapsule(getActivity(), "http://pandalabs.pandasecurity.com/es/wp-content/uploads/2010/03/Mariposaimage.jpg"));
        setCapsule2(new PicassoCapsule(getActivity(), "http://upload.wikimedia.org/wikipedia/commons/5/56/Candy-Corn.jpg"));
        setCapsule3(new PicassoCapsule(getActivity(), "http://6269-9001.zippykid.netdna-cdn.com/wp-content/uploads/2013/09/Dog-Computer-Wallpaper.jpg"));
        setCapsule4(new PicassoCapsule(getActivity(), "http://www.clker.com/cliparts/h/e/A/t/U/Z/red-star-hi.png"));
    }
}
