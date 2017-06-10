package image.manual.android.apptheanime.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import image.manual.android.apptheanime.R;
import image.manual.android.apptheanime.models.AnimeInfor;

/**
 * Created by EDGY on 6/1/2017.
 */

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder>{

    private Context context;
    private List<AnimeInfor> animeInfors;
    private View.OnClickListener onClickListener;

    public AnimeAdapter(Context context, List<AnimeInfor> animeInfors) {
        this.context = context;
        this.animeInfors = animeInfors;
    }

    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_anime, parent, false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(animeInfors.get(position));
    }

    @Override
    public int getItemCount() {
        return animeInfors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_anime)
        ImageView ivAnime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_chapter)
        TextView tvChapter;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }

        public void setData(AnimeInfor animeInfor){
            tvTitle.setText(animeInfor.getName());
            tvAuthor.setText(animeInfor.getAuthor());
            tvChapter.setText(animeInfor.getNumberOfChapter());
            Picasso.with(context).load(animeInfor.getImage()).into(ivAnime);
        }
    }
}
