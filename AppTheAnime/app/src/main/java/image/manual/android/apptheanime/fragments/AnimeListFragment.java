package image.manual.android.apptheanime.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import image.manual.android.apptheanime.R;
import image.manual.android.apptheanime.adapters.AnimeAdapter;
import image.manual.android.apptheanime.models.AnimeInfor;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimeListFragment extends Fragment {

    @BindView(R.id.rv_list_anime)
    RecyclerView rvAnime;

    private List<AnimeInfor> animeInforList = new ArrayList<>();

    public AnimeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_anime_list, container, false);
        setupUI(view);
        loadData();

        return view;
    }

    private void loadData() {

    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        AnimeAdapter animeAdapter = new AnimeAdapter(getContext(), );
    }

}
