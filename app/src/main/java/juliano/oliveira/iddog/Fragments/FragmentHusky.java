package juliano.oliveira.iddog.Fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import juliano.oliveira.iddog.ApiServiceUtils;
import juliano.oliveira.iddog.GET;
import juliano.oliveira.iddog.GridViewAdapter;
import juliano.oliveira.iddog.GridViewScrollListener;
import juliano.oliveira.iddog.IDDogService;
import juliano.oliveira.iddog.ImageDetail;
import juliano.oliveira.iddog.R;
import juliano.oliveira.iddog.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHusky extends Fragment {

    private String _token;

    public FragmentHusky() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_grid_view_husky, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final GridView grid = (GridView) view.findViewById(R.id.grv_Husky);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            _token = bundle.getString("token");
        }

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, Object> hm = grid.getAdapter().getPosition(positon);
                Intent intent = new Intent(getContext(),ImageDetail.class);
                intent.putExtra("IMAGEPATH",products.get(position).imagePath);
                startActivity(intent);
            }
        });

        ApiServiceUtils.GetDog("husky",_token, getContext(), grid);



    }

}
