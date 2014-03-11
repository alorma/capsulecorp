package cat.alorma.capsules.ui.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.alorma.capsulecorp.library.CapsuleView;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;
import cat.alorma.capsules.R;

/**
 * Created by Bernat on 11/03/14.
 */
public class SimpleCapsuleFragment extends Fragment implements TitleStrip{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.simple_capsule, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CapsuleView capsuleView = (CapsuleView) view.findViewById(R.id.capsuleView);
        capsuleView.setCapsule(new TextCapsule("B", Color.RED, Color.BLUE));
    }

    @Override
    public String getTitle() {
        return "Simple capsule";
    }
}
