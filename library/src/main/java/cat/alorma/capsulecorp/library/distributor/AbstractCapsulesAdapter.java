package cat.alorma.capsulecorp.library.distributor;

import android.database.DataSetObserver;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import cat.alorma.capsulecorp.library.capsule.abs.Capsule;

/**
 * Created by Bernat on 11/03/14.
 */
public abstract class AbstractCapsulesAdapter implements Adapter {
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        getRect(position, new Rect(0, 0, 200, 200), 0);

        return null;
    }

    protected abstract void getRect(int position, Rect paintRect, int paddingSize);

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public void add(Capsule capsule) {

    }
}
