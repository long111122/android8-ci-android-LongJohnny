package image.manual.android.freemusic.fragments;


import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import image.manual.android.freemusic.R;
import image.manual.android.freemusic.activities.MainActivity;
import image.manual.android.freemusic.databases.models.TopSongModel;
import image.manual.android.freemusic.events.OnClickMiniPlayer;
import image.manual.android.freemusic.managers.MusicManager;
import image.manual.android.freemusic.managers.ScreenManager;
import jp.wasabeef.picasso.transformations.BlurTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPlayerFragment extends Fragment {
    @BindView(R.id.tv_main_song)
    TextView tvSong;
    @BindView(R.id.tv_main_singer)
    TextView tvSinger;
    @BindView(R.id.iv_close)
    ImageView ivClose;
    @BindView(R.id.iv_download)
    ImageView ivDownload;
    @BindView(R.id.iv_blur)
    ImageView ivBlur;
    @BindView(R.id.iv_main)
    ImageView ivMain;
    @BindView(R.id.sb_main_1)
    SeekBar sbMain1;
    @BindView(R.id.sb_main_2)
    SeekBar sbMain2;
    @BindView(R.id.tv_current_time)
    TextView tvCurrentTime;
    @BindView(R.id.tv_duration)
    TextView tvDuration;
    @BindView(R.id.iv_previous)
    ImageView ivPrevious;
    @BindView(R.id.iv_next)
    ImageView ivNext;
    @BindView(R.id.fb_play)
    FloatingActionButton fbPlay;



    public MainPlayerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_player, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        sbMain1.setPadding(0,0,0,0);
        sbMain2.setPadding(0,0,0,0);
        return view;
    }

    @Subscribe(sticky = true)
    public void onClickMiniPlayer(OnClickMiniPlayer onClickMiniPlayer){
        TopSongModel topSongModel = onClickMiniPlayer.getTopSongModel();
        updateUIMainPlayer(topSongModel);
    }

    public void updateUIMainPlayer(TopSongModel topSongModel){
        tvSong.setText(topSongModel.getSongName());
        tvSinger.setText(topSongModel.getSingerName());
        Picasso.with(getActivity())
                .load(topSongModel.getLargeImage())
                .transform(new BlurTransformation(getContext(), 10))
                .into(ivBlur);
        Picasso.with(getActivity())
                .load(topSongModel.getLargeImage())
                .into(ivMain);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenManager.backFragment(getActivity().getSupportFragmentManager());
            }
        });

        MusicManager.updateSong(fbPlay, sbMain1, sbMain2, tvCurrentTime, tvDuration);

        fbPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicManager.playOrPause();
            }
        });


    }

}
