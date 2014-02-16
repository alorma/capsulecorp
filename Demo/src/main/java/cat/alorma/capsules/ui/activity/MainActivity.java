package cat.alorma.capsules.ui.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cat.alorma.capsules.R;
import cat.alorma.capsules.ui.adapter.NavigationAdapter;
import cat.alorma.capsules.ui.fragment.AnimFragment;
import cat.alorma.capsules.ui.fragment.ColorsFragment;
import cat.alorma.capsules.ui.fragment.ContactsFragment;
import cat.alorma.capsules.ui.fragment.CustomFragment;
import cat.alorma.capsules.ui.fragment.ImagesCapsulesFragment;
import cat.alorma.capsules.ui.fragment.ListCapsulesFragment;
import cat.alorma.capsules.ui.fragment.TextColorsFragment;
import cat.alorma.capsules.ui.fragment.TitleStrip;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private SparseArray<Fragment> fragments;
    private SparseArray<TitleStrip> fragmentsTitles;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        createFragments();

        ActionBar actionBar = getActionBar();

        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);

            setDrawer();
        }
    }

    private void setDrawer() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView mListDrawer = (ListView) findViewById(R.id.listView);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_navigation_drawer, 0, 0) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        List<String> objects = new ArrayList<String>();

        for (int i = 0; i < fragments.size(); i++) {
            TitleStrip t = (TitleStrip) fragments.get(fragments.keyAt(i));
            objects.add(t.getTitle());
        }

        ArrayAdapter<String> adapter = new NavigationAdapter(this, objects);

        mListDrawer.setAdapter(adapter);
        mListDrawer.setOnItemClickListener(this);

        setFragment(0);
    }

    private void setFragment(int position) {
        if (fragments == null) {
            createFragments();
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragments.get(position));
        ft.commit();
    }

    private void createFragments() {
        fragments = new SparseArray<Fragment>();
        fragmentsTitles = new SparseArray<TitleStrip>();

        fragments.put(0, new ColorsFragment());
        fragments.put(1, new TextColorsFragment());
        fragments.put(2, new ImagesCapsulesFragment());
        fragments.put(3, new ListCapsulesFragment());
        fragments.put(4, new CustomFragment());
        fragments.put(5, new AnimFragment());
        fragments.put(6, new ContactsFragment());

        for (int i = 0; i < fragments.size(); i++) {
            TitleStrip t = (TitleStrip) fragments.get(fragments.keyAt(i));
            fragmentsTitles.put(i, t);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }@Override
     public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mDrawerLayout.closeDrawers();

        getActionBar().setTitle(fragmentsTitles.get(fragmentsTitles.keyAt(position)).getTitle());

        setFragment(position);
    }
}
