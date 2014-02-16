package cat.alorma.capsules.ui.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Bernat on 16/02/14.
 */
public class ContactsFragment extends ListFragment implements TitleStrip{

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public String getTitle() {
        return "Contacts";
    }
}
