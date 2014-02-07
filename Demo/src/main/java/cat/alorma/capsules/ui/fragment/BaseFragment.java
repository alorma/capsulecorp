package cat.alorma.capsules.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import cat.alorma.capsulecorp.library.DispenserView;
import cat.alorma.capsulecorp.library.capsule.abs.Capsule;
import cat.alorma.capsules.R;

/**
 * Created by Bernat on 25/11/13.
 */
public abstract class BaseFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    protected DispenserView dispenserView;

    private Capsule capsule1;
    private Capsule capsule2;
    private Capsule capsule3;
    private Capsule capsule4;
    private SeekBar seekBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_main, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dispenserView = (DispenserView) view.findViewById(R.id.dispenserView);
        dispenserView.setMaskResource(R.drawable.mask);

        view.findViewById(R.id.padding0).setOnClickListener(this);
        view.findViewById(R.id.padding10).setOnClickListener(this);
        view.findViewById(R.id.padding50).setOnClickListener(this);
        view.findViewById(R.id.padding100).setOnClickListener(this);

        generateCapsules();

        seekBar = (SeekBar) view.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setMax(3);

        seekBar.setProgress(2);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        changeView(progress);
    }

    private void changeView(int progress) {
        progress++;
        dispenserView.clear();
        switch (progress) {
            case 4:
                dispenserView.addCapsule(getCapsule4());
            case 3:
                dispenserView.addCapsule(getCapsule3());
            case 2:
                dispenserView.addCapsule(getCapsule2());
            case 1:
                dispenserView.addCapsule(getCapsule1());
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        changeView(seekBar.getProgress());
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    protected abstract void generateCapsules();

    public Capsule getCapsule1() {
        return capsule1;
    }

    public void setCapsule1(Capsule capsule1) {
        this.capsule1 = capsule1;
    }

    public Capsule getCapsule2() {
        return capsule2;
    }

    public void setCapsule2(Capsule capsule2) {
        this.capsule2 = capsule2;
    }

    public Capsule getCapsule3() {
        return capsule3;
    }

    public void setCapsule3(Capsule capsule3) {
        this.capsule3 = capsule3;
    }

    public Capsule getCapsule4() {
        return capsule4;
    }

    public void setCapsule4(Capsule capsule4) {
        this.capsule4 = capsule4;
    }

    @Override
    public void onClick(View v) {
        int padding = 0;
        switch (v.getId()) {
            case R.id.padding0:
                padding = 0;
                break;
            case R.id.padding10:
                padding = 10;
                break;
            case R.id.padding50:
                padding = 50;
                break;
            case R.id.padding100:
                padding = 100;
                break;
        }
        dispenserView.setPadding(padding);
    }
}
