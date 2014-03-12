package cat.alorma.capsulecorp.library.distributor;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

import cat.alorma.capsulecorp.library.CapsuleView;
import cat.alorma.capsulecorp.library.capsule.abs.Capsule;

/**
 * Created by Bernat on 11/03/14.
 */
public abstract class AbstractCapsulesAdapter implements ListAdapter {

    private List<Capsule> capsules;
    private Context context;
    private DataSetObserver dataSetObservable;

    public AbstractCapsulesAdapter(Context context) {
        this.context = context;
        capsules = new ArrayList<Capsule>();
    }

    @Override
    public boolean areAllItemsEnabled() {
        for (Capsule c : capsules) {
            if (!c.isEnabled()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return capsules.get(position).isEnabled();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        this.dataSetObservable = observer;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        if (dataSetObservable != null) {
            dataSetObservable.onInvalidated();
            dataSetObservable = null;
        }
    }

    @Override
    public int getCount() {
        return capsules.size();
    }

    @Override
    public Capsule getItem(int position) {
        return capsules.get(position);
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
    public CapsuleView getView(int position, View convertView, ViewGroup parent) {
        Rect parentRect = new Rect();
        parent.getDrawingRect(parentRect);
        Rect rect = getRect(position, parentRect, 0, getCount());

        return new CapsuleView(context, getItem(position), rect);
    }

    protected abstract Rect getRect(int position, Rect paintRect, int paddingSize, int capsulesSize);

    @Override
    public int getItemViewType(int position) {
        return AdapterView.ITEM_VIEW_TYPE_IGNORE;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return capsules.size() == 0;
    }

    public void add(Capsule capsule) {
        this.capsules.add(capsule);
        if (dataSetObservable != null) {
            dataSetObservable.onChanged();
        }
    }
}
