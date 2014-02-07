package cat.alorma.capsules.ui.fragment;

import android.os.Bundle;
import android.view.View;

import cat.alorma.capsules.ui.capsules.CustomType;

/**
 * Created by Bernat on 7/02/14.
 */
public class CustomFragment extends TextColorsFragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dispenserView.setConcretType(new CustomType());
    }
}
