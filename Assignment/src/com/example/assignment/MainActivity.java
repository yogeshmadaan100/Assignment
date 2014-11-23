package com.example.assignment;

import java.util.Locale;

import com.example.fragments.HelpFragment;
import com.example.fragments.LibraryFragment;
import com.example.fragments.PlaylistFragment;
import com.example.fragments.SettingsFragment;
import com.example.fragments.ShopFragment;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	 private DrawerLayout mDrawerLayout;
	    private ListView mDrawerList;
	    private ActionBarDrawerToggle mDrawerToggle;

	    private CharSequence mDrawerTitle;
	    private CharSequence mTitle;
	    private String[] mOptionsTitles;
	    public static int fragment_no;
	    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); 
		mTitle = mDrawerTitle = getTitle();
        mOptionsTitles = getResources().getStringArray(R.array.options_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mOptionsTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0,new LibraryFragment());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
      
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
        	
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        case android.R.id.home:
        	onBackPressed();
        	return true;
       
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        	switch (position) {
			case 0:
				selectItem(position, new LibraryFragment());
				
				break;
			case 1:
				selectItem(position, new PlaylistFragment());
				
				break;
			case 2:
				selectItem(position, new ShopFragment());
				
				break;
			case 3:
				selectItem(position, new SettingsFragment());
				
				break;
			case 4:
				selectItem(position, new HelpFragment());
				
				break;
			
			default:
				selectItem(position, new PlanetFragment());
				break;
			}
           
        }
    }

    public void selectItem(int position, Fragment f) {
        // update the main content by replacing fragments
        Fragment fragment = f;
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        fragment_no=position;
        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
       // setTitle(mOptionsTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Log.e("back", "pressed");
		if(fragment_no!=0)
			selectItem(0, new LibraryFragment());
		else
			finish();
	}
    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.options_array)[i];

            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                            "drawable", getActivity().getPackageName());
           
            getActivity().setTitle(planet);
            return rootView;
        }
    }
    
    @SuppressLint("NewApi")
	public void lock()
    {
    	mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    	//getActionBar().setHomeAsUpIndicator(R.drawable.ic_ab_back_holo_dark_am);
    	//getActionBar().setHomeButtonEnabled(true);
    	mDrawerToggle.setDrawerIndicatorEnabled(false);
    }
    public void unlock()
    {
    	mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    	mDrawerToggle.setDrawerIndicatorEnabled(true);
    	getActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
    	//getActionBar().setHomeButtonEnabled(false);
    	//getActionBar().setHomeAsUpIndicator(0);
    }
    public void open()
    {
    	mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    	//getActionBar().setHomeAsUpIndicator(R.drawable.ic_ab_back_holo_dark_am);
    	//getActionBar().setHomeButtonEnabled(true);
    	mDrawerToggle.setDrawerIndicatorEnabled(false);
    }
}
