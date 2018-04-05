package juliano.oliveira.iddog.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import juliano.oliveira.iddog.GridViewAdapter;
import juliano.oliveira.iddog.GridViewScrollListener;
import juliano.oliveira.iddog.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHusky extends Fragment {


    public FragmentHusky() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View inflate = inflater.inflate(R.layout.fragment_grid_view_husky, container, false);
        Bundle args = getArguments();
        ArrayList<String> index = args.getStringArrayList("index");
        return inflate;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        GridView grid = (GridView) view.findViewById(R.id.grv_Husky);

        grid.setAdapter(new GridViewAdapter(getContext()));
        grid.setOnScrollListener(new GridViewScrollListener(getContext()));
    }

}
