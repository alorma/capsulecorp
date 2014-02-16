package cat.alorma.capsules.ui.fragment;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import cat.alorma.capsules.ui.adapter.ContactsAdapter;

/**
 * Created by Bernat on 16/02/14.
 */
public class ContactsFragment extends ListFragment implements TitleStrip, LoaderManager.LoaderCallbacks<Cursor> {

    private static String DISPLAY_NAME = Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.HONEYCOMB ?
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
            ContactsContract.Contacts.DISPLAY_NAME;

    @SuppressLint("InlinedApi")
    private static final String[] PROJECTION = {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            DISPLAY_NAME};

    private ContactsAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new ContactsAdapter(getActivity(), null);
        setListAdapter(adapter);

        if (getLoaderManager() != null) {
            getLoaderManager().initLoader(0, null, this);
        }
    }

    @Override
    public String getTitle() {
        return "Contacts";
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(
                getActivity(),
                ContactsContract.Contacts.CONTENT_URI,
                PROJECTION,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
