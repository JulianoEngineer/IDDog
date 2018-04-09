package juliano.oliveira.iddog;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class IDDogViewer extends AppCompatActivity {

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
}
