package group.ripple.dhamaschool;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;


public class Home extends Activity {
       String mTitle="";
    Fragment news_feed = new MainActivity();
    Fragment about_us = new About_Us_Fragment();
    Fragment facebook_fragment = new FacebookPage();
    Fragment contact_fragment = new Contact_fragment();
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    String[] values;
    public static final String MIXPANEL_TOKEN = "f2f8f6664653a1d64042ecd6a28653a5";

    MixpanelAPI mixpanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        String udid = android.provider.Settings.System.getString(
                this.getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);


        mixpanel = MixpanelAPI.getInstance(getApplicationContext(), MIXPANEL_TOKEN);
        JSONObject props = new JSONObject();

        try {
            props.put("id",udid);
            props.put("name","Dhamma School");

        } catch (JSONException e) {
            e.printStackTrace();
        }

      //  Log.e("ididid", mixpanel.getDistinctId());

        mixpanel.registerSuperProperties(props);
        mixpanel.identify(mixpanel.getDistinctId());
        mixpanel.track("Plan Selected", props);

        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        mTitle = "DhammaSchoool Foundaton";
       ab.setTitle(mTitle);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);


        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle("DhammaSchool");
                invalidateOptionsMenu();
            }
        };



        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        values = new String[]{
                "News Feed",
                "Facebook Page",
                "Contact Information",
                "About Us"
        };


        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if(savedInstanceState ==null){
            selectItem(0);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            selectItem(position);

            }
        });
    }

    private void selectItem(int position) {

        mTitle = values[position];
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        // Locate Position
        switch (position) {
            case 0:
                ft.replace(R.id.content_frame, news_feed);
                break;
            case 1:
                ft.replace(R.id.content_frame,facebook_fragment);
                break;

            case 2:
                ft.replace(R.id.content_frame,contact_fragment);
                break;
            case 3:
                ft.replace(R.id.content_frame,about_us);
                break;
        }
        ft.commit();
        mDrawerList.setItemChecked(position, true);
        // Get the title followed by the position
        getActionBar().setTitle(mTitle);
        // Close drawer
        mDrawerLayout.closeDrawer(mDrawerList);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        return super.onOptionsItemSelected(item);
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
    protected void onDestroy() {
        mixpanel.flush();
        super.onDestroy();
    }


}