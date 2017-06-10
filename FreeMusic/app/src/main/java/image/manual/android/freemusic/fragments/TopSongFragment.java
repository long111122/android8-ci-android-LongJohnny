package image.manual.android.freemusic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import image.manual.android.freemusic.R;
import image.manual.android.freemusic.adapters.TopSongAdapter;
import image.manual.android.freemusic.databases.models.MusicTypeModel;
import image.manual.android.freemusic.databases.models.TopSongModel;
import image.manual.android.freemusic.events.OnCLickMusicTypeModel;
import image.manual.android.freemusic.events.OnClickTopSong;
import image.manual.android.freemusic.managers.MusicManager;
import image.manual.android.freemusic.managers.ScreenManager;
import image.manual.android.freemusic.networks.RetrofitFactory;
import image.manual.android.freemusic.networks.music_type.top_songs.top_songs.EntryObject;
import image.manual.android.freemusic.networks.music_type.top_songs.top_songs.Feed;
import image.manual.android.freemusic.networks.music_type.top_songs.top_songs.MainObject;
import image.manual.android.freemusic.networks.service.TopSongService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "TopSongFragment";
    @BindView(R.id.iv_top_song)
    ImageView ivMusicType;
    @BindView(R.id.tv_top_song)
    TextView tvMusicType;
    @BindView(R.id.rv_top_song)
    RecyclerView rvTopSong;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private List<TopSongModel> topSongModelList = new ArrayList<>();
    private TopSongAdapter topSongAdapter;

    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_songs, container, false);
        setupUI(view);
        
        return view;
    }

    private void loadData(MusicTypeModel musicTypeModel) {
        final TopSongService topSongService = RetrofitFactory
                .getInstance()
                .createService(TopSongService.class);
        topSongService.getTopSongs(musicTypeModel.getId())
                .enqueue(new Callback<MainObject>() {
                    @Override
                    public void onResponse(Call<MainObject> call, Response<MainObject> response) {
                        MainObject naMainObject = response.body();
                        Feed feed = naMainObject.getFeed();
                        for (EntryObject entryObject : feed.getEntryObjectList()){
                            topSongModelList.add(new TopSongModel(
                                    entryObject.getNameObject().getLabel(),
                                    entryObject.getImageObjectList().get(1).getLabel(),
                                    entryObject.getImageObjectList().get(2).getLabel(),
                                    entryObject.getArtistObject().getLabel()
                            ));

                        }
                        topSongAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<MainObject> call, Throwable t) {

                    }
                });
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        topSongAdapter = new TopSongAdapter(getContext(), topSongModelList);
        rvTopSong.setAdapter(topSongAdapter);
        rvTopSong.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                getContext(),
                DividerItemDecoration.VERTICAL);
        rvTopSong.addItemDecoration(dividerItemDecoration);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenManager.backFragment(getActivity().getSupportFragmentManager());
            }
        });
        topSongAdapter.setOnItemClick(this);
    }

    @Subscribe (sticky = true)
    public void onReceivedMusicType(OnCLickMusicTypeModel onCLickMusicTypeModel){
        MusicTypeModel musicTypeModel = onCLickMusicTypeModel.getMusicTypeModel();
        tvMusicType.setText(musicTypeModel.getKey());
        ivMusicType.setBackgroundResource(musicTypeModel.getIdImage());
        loadData(musicTypeModel);
    }


    @Override
    public void onClick(View v) {
        TopSongModel topSongModel = (TopSongModel) v.getTag();
        EventBus.getDefault().post(new OnClickTopSong(topSongModel));
    }
}
