package group.ripple.dhamaschool;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class DetailActivity extends ActionBarActivity {
       @InjectView(R.id.viewpager)
    ViewPager vg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.inject(this);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff0f9d58")));
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("DhammaSchool Foundation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NewsDao newsDao = new NewsDao(getApplicationContext(),new DatabaseManager(getApplicationContext()));
        ArrayList<NewsObj> al = new ArrayList<NewsObj>();
        al = newsDao.getAll();
        DetailPagerFB adapter = new DetailPagerFB(getApplicationContext(),al);
        vg.setAdapter(adapter);
        vg.setCurrentItem(getIntent().getIntExtra("position",0));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
