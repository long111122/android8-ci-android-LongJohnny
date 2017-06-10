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
import image.manual.android.freemusic.R;
import image.manual.android.freemusic.databases.models.TopSongModel;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

/**
 * Created by EDGY on 5/30/2017.
 */

public class TopSongAdapter extends RecyclerView.Adapter<TopSongAdapter.ViewHolderItem>{

    private Context context;
    private List<TopSongModel> topSongModelList;
    private View.OnClickListener onClickListener;

    public TopSongAdapter(Context context, List<TopSongModel> topSongModelList) {
        this.context = context;
        this.topSongModelList = topSongModelList;
    }

    public void setOnItemClick(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_list_top_song, parent, false);
        itemView.setOnClickListener(onClickListener);
        return new ViewHolderItem(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderItem holder, int position) {
        holder.setData(topSongModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return topSongModelList.size();
    }

    public class ViewHolderItem extends RecyclerView.ViewHolder{

        @BindView(R.id.iv_item_top_song)
        ImageView ivTopSong;
        @BindView(R.id.tv_item_top_song)
        TextView tvTopSong;
        @BindView(R.id.tv_singer_top_song)
        TextView tvSinger;
        View view;

        public ViewHolderItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }

        public void setData(TopSongModel topSongModel){
            Picasso.with(context).load(topSongModel.getSmallImage()).transform(new CropCircleTransformation()).into(ivTopSong);
            tvTopSong.setText(topSongModel.getSongName());
            tvSinger.setText(topSongModel.getSingerName());
            view.setTag(topSongModel);
        }
    }
}
