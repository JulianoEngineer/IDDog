package juliano.oliveira.iddog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.io.IOException;

import juliano.oliveira.iddog.Fragments.FragmentHound;
import juliano.oliveira.iddog.Fragments.FragmentHusky;
import juliano.oliveira.iddog.Fragments.FragmentLabrador;
import juliano.oliveira.iddog.Fragments.FragmentPug;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GridViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String _token;
    private IDDogService _apiService;
    private Object flag;
    private Fragment globalFragment;
    private ProgressDialog _progressDialog;

    public GridViewPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        flag = new Object();
        _progressDialog = new ProgressDialog(context);
        _progressDialog.setIndeterminate(true);
        _progressDialog.setMessage("Authenticating...");
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putString("token",_token);

            switch (position){
                case 0:
                    Fragment husky = new FragmentHusky();
                    husky.setArguments(bundle);
                    return husky;
                case 1:
                    Fragment hound = new FragmentHound();
                    hound.setArguments(bundle);
                    return hound;
                case 2:
                    Fragment pug = new FragmentPug();
                    pug.setArguments(bundle);
                    return pug;
                case 3:
                    Fragment labrador = new FragmentLabrador();
                    labrador.setArguments(bundle);
                    return labrador;
                  default:
                     Fragment defaulFrag = new FragmentHusky();
                     defaulFrag.setArguments(bundle);
                     return defaulFrag;
            }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 4;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.fragment_husky);
            case 1:
                return mContext.getString(R.string.fragment_hound);
            case 2:
                return mContext.getString(R.string.fragment_pug);
            case 3:
                return mContext.getString(R.string.fragment_labrador);
            default:
                return null;
        }
    }

    public void setToken(String token)
    {
        this._token = token;
    }

    public void setApiService(IDDogService apiService)
    {
        this._apiService = apiService;
    }

}