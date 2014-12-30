package group.ripple.dhamaschool;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Fragment {
    @InjectView(R.id.feed)
    ListView feedListView;
    @InjectView(R.id.swipe_to_refresh_list)
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<NewsObj> al;
    NewsDao newsDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.activity_main,container,false);
        ButterKnife.inject(this,v);


        al = new ArrayList<NewsObj>();
        newsDao = new NewsDao(getActivity(),new DatabaseManager(getActivity()));
        showData();
        swipeRefreshLayout.setColorSchemeResources(R.color.swipe_refresh_color1,
                R.color.swipe_refresh_color2,
                R.color.swipe_refresh_color3,
                R.color.swipe_refresh_color4);

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            loadTips();

        }
    });

    return v;
    }


    public void StartRefreshing() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    public void StopRefreshing() {
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    public void loadTips() {
        if (NetworkListener.isOnline(getActivity())){
            StartRefreshing();
            Ion.with(this)
                    .load(Constant.URL)
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {
                            try {
                                parseJSON(result);
                            } catch (JSONException e1) {
                                e1.printStackTrace();
                            }
                            showData();
                            StopRefreshing();
                        }
                    });
        }
        else{

            new MaterialDialog.Builder(getActivity())

                    .content("No internet connection!, Pls, Enable data-connection and try again!")
                    .positiveText("Ok")
                    .callback(new MaterialDialog.FullCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            swipeRefreshLayout.setRefreshing(false);
                        }

                        @Override
                        public void onNeutral(MaterialDialog dialog) {

                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {

                        }
                    })
                    .show();

        }


    }



    private void parseJSON(String result) throws JSONException {
        // TODO Auto-generated method stub

        JSONObject response = new JSONObject(result);
        try {
            JSONArray jsonArray = response.getJSONArray("entries");
            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject jsonobj1 = jsonArray.getJSONObject(i);
                NewsObj fbnewsobj = new NewsObj();
                fbnewsobj.setId(jsonobj1.getString("id"));
                fbnewsobj.setContent(jsonobj1.getString("content"));
                fbnewsobj.setUpdated(jsonobj1.getString("updated"));
                newsDao.CRUD(fbnewsobj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showData(){
        al = newsDao.getAll();
        NewsObjAdapter adapter = new NewsObjAdapter(getActivity(),al);
        feedListView.setAdapter(adapter);

        feedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getActivity(),DetailActivity.class);
                it.putExtra("position",i);
                startActivity(it);
            }
        });
    }




}







