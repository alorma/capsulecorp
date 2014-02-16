package cat.alorma.capsules.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import cat.alorma.capsulecorp.library.DispenserView;
import cat.alorma.capsulecorp.library.capsule.impl.ContactCapsule;
import cat.alorma.capsules.R;
import cat.alorma.capsules.ui.capsules.AQueryCapsule;

/**
 * Created by Bernat on 16/02/14.
 */
public class ContactsAdapter extends CursorAdapter {

    private String DISPLAY_NAME = Build.VERSION.SDK_INT
            >= Build.VERSION_CODES.HONEYCOMB ?
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY :
            ContactsContract.Contacts.DISPLAY_NAME;

    private final LayoutInflater layoutInflater;

    public ContactsAdapter(Context context, Cursor c) {
        this(context, c, true);
    }

    public ContactsAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.row, null);

        if (v != null) {
            v.setTag(ViewHolder.create(v));
        }

        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        if (view.getTag() != null) {
            ViewHolder vh = (ViewHolder) view.getTag();

            long id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
            String lookup = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));

            vh.textView.setText(name);

            Uri uri = ContactsContract.Contacts.getLookupUri(id, lookup);

            vh.dispenserView.clear();
            vh.dispenserView.addCapsule(new ContactCapsule(context, lookup, uri, Color.BLACK, Color.RED));
        }
    }

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
}
