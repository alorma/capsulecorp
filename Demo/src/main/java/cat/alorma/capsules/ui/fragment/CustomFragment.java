package cat.alorma.capsules.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsulecorp.library.capsule.abs.EmptyCapsule;
import cat.alorma.capsulecorp.library.capsule.impl.TextCapsule;
import cat.alorma.capsulecorp.library.type.Type;
import cat.alorma.capsules.ui.capsules.CustomType;
import cat.alorma.capsules.ui.capsules.CustomTypeFive;

public class CustomFragment extends BaseFragment {

    @Override
    protected Capsule getCapsule1() {
        return new TextCapsule("1", Color.parseColor("#000000"), Color.parseColor("#e67e22"));
    }

    @Override
    protected Capsule getCapsule2() {
        return new EmptyCapsule();
    }

    @Override
    protected Capsule getCapsule3() {
        return new EmptyCapsule();
    }

    @Override
    protected Capsule getCapsule4() {
        return new TextCapsule("4", Color.parseColor("#000000"), Color.parseColor("#1abc9c"));
    }

    @Override
    protected Type getType() {
        return new CustomTypeFive();
    }
}
