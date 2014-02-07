package cat.alorma.capsules.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cat.alorma.capsulecorp.library.DispenserView;
import cat.alorma.capsules.R;
import cat.alorma.capsules.model.Data;

/**
 * Created by Bernat on 25/11/13.
 */


public class CapsulesAdapter extends ArrayAdapter<Data> {

    private LayoutInflater mInflater;
    private boolean isFling;
    private boolean maskEnabled = false;

    // Constructors
    public CapsulesAdapter(Context context, List<Data> objects) {
        super(context, 0, objects);
        this.mInflater = LayoutInflater.from(context);
    }

    public CapsulesAdapter(Context context, Data[] objects) {
        super(context, 0, objects);
        this.mInflater = LayoutInflater.from(context);
    }

    public void setFling(boolean fling) {
        this.isFling = fling;
        this.notifyDataSetChanged();
    }

    public void setMaskEnabled() {
        this.maskEnabled = !maskEnabled;
        this.notifyDataSetChanged();
    }

    /**
     * ViewHolder class for layout.<br />
     * <br />
     * Auto-created on 2013-11-25 23:10:40 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private static class ViewHolder {
        public final View rootView;
        public final DispenserView dispenserView;
        public final TextView textView;

        private ViewHolder(View rootView, DispenserView dispenserView, TextView textView) {
            this.rootView = rootView;
            this.dispenserView = dispenserView;
            this.textView = textView;
        }

        public static ViewHolder create(View rootView) {
            DispenserView dispenserView = (DispenserView) rootView.findViewById(R.id.dispenserView);
            TextView textView = (TextView) rootView.findViewById(R.id.textView);
            return new ViewHolder(rootView, dispenserView, textView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        Data data = getItem(position);
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.row, parent, false);
            vh = ViewHolder.create(view);
            if (view != null) {
                view.setTag(vh);
            }
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        if (!isFling) {
            vh.textView.setText(data.getName());
            if (data.getCapsules() != null) {
                vh.dispenserView.clear();
                for (int i = 0; i < (data.getCapsules().size() <= 4 ? data.getCapsules().size() : 4); i++) {
                    vh.dispenserView.addCapsule(data.getCapsules().get(i));
                }
                vh.dispenserView.setMaskEnabled(maskEnabled);
            }
        }

        return vh.rootView;
    }


}
