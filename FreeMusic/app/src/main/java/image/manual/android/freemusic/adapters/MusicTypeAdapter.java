package image.manual.android.freemusic.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import image.manual.android.freemusic.MusicType;
import image.manual.android.freemusic.R;
import image.manual.android.freemusic.databases.models.MusicTypeModel;
import image.manual.android.freemusic.fragments.MusicTypeFragment;

/**
 * Created by EDGY on 5/28/2017.
 */

public class MusicTypeAdapter extends RecyclerView.Adapter<MusicTypeAdapter.MusicTypeViewHolder> {

    private List<MusicTypeModel> musicTypeModels;
    private Context context;

    private View.OnClickListener onClickListener;

    public void setOnItemClickListener (View.OnClickListener onItemClickListener){
        this.onClickListener = onItemClickListener;
    }

    public MusicTypeAdapter(List<MusicTypeModel> musicTypeModels, Context context) {
        this.musicTypeModels = musicTypeModels;
        this.context = context;
    }

    //2
    @Override
    public MusicTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_music_types, parent, false);
        itemView.setOnClickListener(onClickListener);
        return new MusicTypeViewHolder(itemView);
    }

    //3
    @Override
    public void onBindViewHolder(MusicTypeViewHolder holder, int position) {
        holder.setData(musicTypeModels.get(position));
    }

    //1
    @Override
    public int getItemCount() {
        return musicTypeModels.size();
    }


    public class MusicTypeViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_music_type)
        ImageView ivMusicType;
        @BindView(R.id.tv_music_type)
        TextView tvMusicType;
        View view;

        public MusicTypeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }

        public void setData(MusicTypeModel musicTypeModel){
            Picasso.with(context).load(musicTypeModel.getIdImage()).into(ivMusicType);
            tvMusicType.setText(musicTypeModel.getKey());
            view.setTag(musicTypeModel);
        }
    }
}
