package juliano.oliveira.iddog.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import juliano.oliveira.iddog.ApiServiceUtils;
import juliano.oliveira.iddog.ImageDetail;
import juliano.oliveira.iddog.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPug extends Fragment {

    private String _token;

    public FragmentPug() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_view_pug, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        final GridView grid = (GridView) view.findViewById(R.id.grv_Pug);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            _token = bundle.getString("token");
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

        ApiServiceUtils.GetDog("pug",_token, getContext(), grid);

    }

}
