package image.manual.android.freemusic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import image.manual.android.freemusic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment {

    @BindView(R.id.iv_music_type)
    ImageView ivMusicType;
    @BindView(R.id.tv_music_type)
    TextView tvMusicType;
    @BindView(R.id.rv_top_song)
    RecyclerView rvTopSong;

    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_songs, container, false);

        return view;
    }

}
