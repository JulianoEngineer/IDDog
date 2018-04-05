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
        try {
            Response<GET> response;

            switch (position){
                case 0:
                    synchronized (flag)
                    {
                        GetDog(_apiService,"husky",_token , 0);
                        _progressDialog.show();
                        flag.wait();
                        _progressDialog.dismiss();
                    }
                    return globalFragment;
                case 1:
                    return new FragmentHound();
                case 2:
                    return new FragmentPug();
                case 3:
                    return new FragmentLabrador();

                 default:
                    return new FragmentHusky();
            }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        return null;
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

    public void GetDog(IDDogService api, String category,  String token, final int position) {

        api.getCategory(category, token).enqueue(new Callback<GET>() {
            @Override
            public void onResponse(Call<GET> call, Response<GET> response) {
                if (response.isSuccessful()) {

                    switch (position) {
                        case 0:
                            Fragment husky = new FragmentHusky();
                            Bundle args = new Bundle();
                            args.putStringArrayList("index", response.body().getList());
                            globalFragment = husky;
                            flag.notifyAll();
                        case 1:

                        case 2:

                        case 3:

                    }
                }
            }

            @Override
            public void onFailure(Call<GET> call, Throwable t) {

            }
        });
    }

}