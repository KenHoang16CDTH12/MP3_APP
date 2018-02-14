package it.hueic.kenhoang.mp3_app.Adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import it.hueic.kenhoang.mp3_app.Interface.ItemClickListener;
import it.hueic.kenhoang.mp3_app.R;

/**
 * Created by kenhoang on 07/02/2018.
 */

public class ListSongsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView tvName, tvDesc;
    public ImageView imgStatus, imgDownload;
    public ItemClickListener itemClickListener;
    public ListSongsViewHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.name);
        tvDesc = itemView.findViewById(R.id.desc);
        imgStatus = itemView.findViewById(R.id.imgStatus);
        imgDownload = itemView.findViewById(R.id.imgDownload);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
