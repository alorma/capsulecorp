package cat.alorma.capsules.ui.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import cat.alorma.capsules.ui.fragment.ImagesCapsulesFragment;
import cat.alorma.capsules.ui.fragment.ListCapsulesFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(android.R.id.content, new ImagesCapsulesFragment());
        fragmentTransaction.commit();
    }
}
