package image.manual.android.apptheanime.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import image.manual.android.apptheanime.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimeListFragment extends Fragment {


    public AnimeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anime_list, container, false);


        return view;
    }

}
