package juliano.oliveira.iddog.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import juliano.oliveira.iddog.ApiServiceUtils;
import juliano.oliveira.iddog.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLabrador extends Fragment {

    private String _token;

    public FragmentLabrador() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_view_labrador, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        GridView grid = (GridView) view.findViewById(R.id.grv_Labrador);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            _token = bundle.getString("token");
        }

        ApiServiceUtils.GetDog("labrador",_token, getContext(), grid);

    }

}
