package juliano.oliveira.iddog.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import juliano.oliveira.iddog.ApiAccess.ApiServiceUtils;
import juliano.oliveira.iddog.ImageDetail;
import juliano.oliveira.iddog.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class GenericGridFragment extends Fragment {

    private String _token;
    private String _category;

    public GenericGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_grid_generic, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final GridView grid = (GridView) view.findViewById(R.id.grv_generic_dog);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            _token = bundle.getString("token");
            _category = bundle.getString("category");
        }

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = grid.getAdapter().getItem(position).toString();
                Intent intent = new Intent(getContext(), ImageDetail.class);
                intent.putExtra("IMAGEPATH",path);
                startActivity(intent);
            }
        });

        if(_category != null && _token != null) {
            ApiServiceUtils.GetDog(_category, _token, getContext(), grid);
        }else{
            Toast.makeText(getContext(),"Problem acessing service",Toast.LENGTH_LONG);
        }

    }

}
