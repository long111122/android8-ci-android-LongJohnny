package image.manual.android.freemusic.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import image.manual.android.freemusic.MediaType;
import image.manual.android.freemusic.MusicType;
import image.manual.android.freemusic.MusicTypeService;
import image.manual.android.freemusic.R;
import image.manual.android.freemusic.adapters.MusicTypeAdapter;
import image.manual.android.freemusic.databases.models.MusicTypeModel;
import image.manual.android.freemusic.events.OnCLickMusicTypeModel;
import image.manual.android.freemusic.managers.ScreenManager;
import image.manual.android.freemusic.networks.RetrofitFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicTypeFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MusicTypeFragment";
    @BindView(R.id.rv_type_musics)
    RecyclerView rvMusicType;

    private List<MusicTypeModel> musicTypeModelList = new ArrayList<>();
    private MusicTypeAdapter musicTypeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_type, container, false);
        setupUI(view);
        loadData();
        return view;
    }

    private void loadData() {
        RetrofitFactory
                .getInstance()
                .createService(MusicTypeService.class).getMusicTypes().enqueue(new Callback<List<MediaType>>() {
            @Override
            public void onResponse(Call<List<MediaType>> call, Response<List<MediaType>> response) {
                if(response.code() == 200) {
                    MediaType mediaType = response.body().get(3);
                    List<MusicType> musicTypes = mediaType.getSubgenres();
                    for (MusicType musicType : musicTypes){
                        MusicTypeModel musicTypeModel = new MusicTypeModel(
                                musicType.getId(),
                                musicType.getTranslation_key(),
                                getResources().getIdentifier(
                                        "genre_x2_" + musicType.getId(),
                                        "raw",
                                        getActivity().getPackageName())
                        );
                        musicTypeModelList.add(musicTypeModel);
                    }
                    musicTypeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<MediaType>> call, Throwable t) {
                Log.d(TAG, "fail connecction");
            }
        });
    }

    private void setupUI(View view) {
        ButterKnife.bind(this, view);
        musicTypeAdapter = new MusicTypeAdapter(musicTypeModelList, getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getContext(),
                2,
                LinearLayoutManager.VERTICAL,
                false
        );
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position % 3 == 0 ? 2 : 1);
            }
        });
        rvMusicType.setAdapter(musicTypeAdapter);
        rvMusicType.setLayoutManager(gridLayoutManager);
        musicTypeAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MusicTypeModel musicTypeModel = (MusicTypeModel) v.getTag();
        Log.d(TAG,musicTypeModel.getKey() + "");
        EventBus.getDefault().postSticky(new OnCLickMusicTypeModel(musicTypeModel));
        ScreenManager.openFragment(getActivity().getSupportFragmentManager(), new TopSongFragment(), R.id.rl_container , true);
    }
}
