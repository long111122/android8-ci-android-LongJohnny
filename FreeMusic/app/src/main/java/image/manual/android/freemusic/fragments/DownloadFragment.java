package image.manual.android.freemusic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import image.manual.android.freemusic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownloadFragment extends Fragment {


    public DownloadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_download2, container, false);
    }

}
