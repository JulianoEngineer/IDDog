package juliano.oliveira.iddog;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import juliano.oliveira.iddog.ApiAccess.ApiServiceUtils;
import juliano.oliveira.iddog.ApiAccess.IDDogService;
import juliano.oliveira.iddog.GridUtils.GridViewPagerAdapter;

public class IDDogViewer extends AppCompatActivity {

    private boolean _doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_dog_viewer);

        String _token = getIntent().getStringExtra("TOKEN");
        IDDogService _apiService = ApiServiceUtils.getAPIService();

        ViewPager viewPager = (ViewPager) findViewById(R.id.vw_dogViewer);

        GridViewPagerAdapter adapter = new GridViewPagerAdapter(this, getSupportFragmentManager());
        adapter.setToken(_token);
        adapter.setApiService(_apiService);

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sld_tabsViewer);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (_doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this._doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.logoff_message, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                _doubleBackToExitPressedOnce =false;
            }
        }, 2000);
    }
}
