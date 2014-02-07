package cat.alorma.capsules.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import cat.alorma.capsules.R;
import cat.alorma.capsules.ui.fragment.ColorsFragment;
import cat.alorma.capsules.ui.fragment.CustomFragment;
import cat.alorma.capsules.ui.fragment.ImagesCapsulesFragment;
import cat.alorma.capsules.ui.fragment.ListCapsulesFragment;
import cat.alorma.capsules.ui.fragment.TextColorsFragment;

public class MainActivity extends Activity implements ActionBar.TabListener {

    private SparseArray<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getActionBar() != null) {
            getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            List<String> titles = new ArrayList<String>();
            titles.add("Colors");
            titles.add("Texts");
            titles.add("Images");
            titles.add("List");
            titles.add("Custom");

            for (String str : titles) {
                ActionBar.Tab tab = getActionBar().newTab();
                tab.setText(str);
                tab.setTabListener(this);
                getActionBar().addTab(tab);
            }
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (fragments == null) {
            createFragments();
        }
        ft.replace(android.R.id.content, fragments.get(tab.getPosition()));
    }

    private void createFragments() {
        fragments = new SparseArray<Fragment>();

        fragments.put(0, new ColorsFragment());
        fragments.put(1, new TextColorsFragment());
        fragments.put(2, new ImagesCapsulesFragment());
        fragments.put(3, new ListCapsulesFragment());
        fragments.put(4, new CustomFragment());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
}
