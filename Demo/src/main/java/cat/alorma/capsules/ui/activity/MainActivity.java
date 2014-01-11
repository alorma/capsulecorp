package cat.alorma.capsules.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import cat.alorma.capsules.ui.fragment.ColorsFragment;
import cat.alorma.capsules.ui.fragment.ImagesCapsulesFragment;
import cat.alorma.capsules.ui.fragment.ListCapsulesFragment;
import cat.alorma.capsules.ui.fragment.TextColorsFragment;

public class MainActivity extends Activity implements ActionBar.TabListener {

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

            for (String str:titles) {
                ActionBar.Tab tab = getActionBar().newTab();
                tab.setText(str);
                tab.setTabListener(this);
                getActionBar().addTab(tab);
            }
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        switch (tab.getPosition()) {
            case 0:
                ft.replace(android.R.id.content, new ColorsFragment());
                break;
            case 1:
                ft.replace(android.R.id.content, new TextColorsFragment());
                break;
            case 2:
                ft.replace(android.R.id.content, new ImagesCapsulesFragment());
                break;
            case 3:
                ft.replace(android.R.id.content, new ListCapsulesFragment());
                break;
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
