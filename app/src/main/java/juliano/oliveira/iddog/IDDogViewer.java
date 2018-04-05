package juliano.oliveira.iddog;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class IDDogViewer extends AppCompatActivity {

    private String _token;
    private IDDogService _apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_id_dog_viewer);

        _token = getIntent().getStringExtra("TOKEN");
        _apiService = ApiServiceUtils.getAPIService();

        ViewPager viewPager = (ViewPager) findViewById(R.id.vw_dogViewer);

        GridViewPagerAdapter adapter = new GridViewPagerAdapter(this, getSupportFragmentManager());
        adapter.setToken(_token);
        adapter.setApiService(_apiService);

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sld_tabsViewer);
        tabLayout.setupWithViewPager(viewPager);
    }
}
