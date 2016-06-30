package id.tech.rcslive.activity;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by macbook on 4/5/16.
 */
public class SearchEvent extends AppCompatActivity {
    @Bind(R.id.wrapper_search_event_text)View wrapper_search;
    @Bind(R.id.wrapper_search_event_spinner)View wrapper_search_event_spinner;
//    @OnClick(R.id.wrapper_search_event_text) void

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        ActionBar ac = getSupportActionBar();
        ac.setDisplayHomeAsUpEnabled(true);
        ac.setTitle("Search Event ");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();

        menuInflater.inflate(R.menu.menu_search, menu);

//        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
//        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            case R.id.action_search:
                if(wrapper_search.getVisibility() == View.GONE){
                    wrapper_search.setVisibility(View.VISIBLE);
                    wrapper_search_event_spinner.setVisibility(View.GONE);
                }else{
                    wrapper_search.setVisibility(View.GONE);
                    wrapper_search_event_spinner.setVisibility(View.VISIBLE);
                }

                break;
        }
        return true;
    }
}
