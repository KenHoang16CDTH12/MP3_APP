package it.hueic.kenhoang.mp3_app.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import it.hueic.kenhoang.mp3_app.Activity.LoginActivity;
import it.hueic.kenhoang.mp3_app.Activity.PlayActivity;
import it.hueic.kenhoang.mp3_app.Adapter.ViewHolder.ListSongsViewHolder;
import it.hueic.kenhoang.mp3_app.Interface.ItemClickListener;
import it.hueic.kenhoang.mp3_app.Model.Song;
import it.hueic.kenhoang.mp3_app.R;

/**
 * Created by kenhoang on 07/02/2018.
 */

public class ListSongAdapter extends RecyclerView.Adapter<ListSongsViewHolder> {
    private List<Song> songs = new ArrayList<>();
    private Activity context;

    public ListSongAdapter(List<Song> songs, Activity context) {
        this.songs = songs;
        this.context = context;
    }

    @Override
    public ListSongsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_recycler_songs, parent, false);
        return new ListSongsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListSongsViewHolder holder, int position) {
        Song model = songs.get(position);
        holder.tvName.setText(model.getName());
        holder.tvDesc.setText(model.getUrl());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                //Create intent
                Intent intent = new Intent(context, PlayActivity.class);
                intent.putExtra("id", position);
                context.setResult(context.RESULT_OK, intent);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }
}
