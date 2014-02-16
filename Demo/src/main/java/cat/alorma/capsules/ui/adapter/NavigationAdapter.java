package cat.alorma.capsules.ui.adapter;

/**
 * Created by Bernat on 16/02/14.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cat.alorma.capsules.R;

public class NavigationAdapter extends ArrayAdapter<String> {

    private final LayoutInflater layoutInflater;

    public NavigationAdapter(Context context, List<String> objects) {
        super(context, R.layout.row_navigation, objects);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.row_navigation;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        super.getView(position, convertView, parent);

        TextView txt = (TextView) layoutInflater.inflate(getItemViewType(position), parent, false);

        if (txt != null) {
            txt.setText(getItem(position));
        }

        return txt;
    }
}
